package com.spring.test.kafka.helper;

import com.spring.test.kafka.helper.KafkaInstance.KafkaInstance;
import com.spring.test.model.message.QueueMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class HandleMessage {


    public Pair<Set<Object>, List<String>> handleMessage(QueueMessage message, boolean commit){

        KafkaInstance kafkaInstance = KafkaInstance.getInstance();
        KafkaConsumer consumer = kafkaInstance.createConsumer();

        List<PartitionInfo> partitions = (List<PartitionInfo>)consumer.listTopics().get(message.getTopic());
        if(partitions == null || partitions.isEmpty()){
            System.out.println("the topic is null, please check the topic name");
        }

        CountDownLatch countDownLatch = new CountDownLatch(partitions.size());

        Set<Object> objectSet = Collections.synchronizedSet(new HashSet<>());
        List<String> keyList = Collections.synchronizedList(new ArrayList<>());
        Flux.fromIterable(partitions)
                .parallel(partitions.size())     //将任务分发到5个不同的线程进行处理
                .runOn(Schedulers.parallel())  // 将订阅者进行分轨
                .subscribe(partitionInfo -> {
                    //获取每个partition里面的message
                    searchMessageFromPartition(message, partitionInfo, commit, objectSet, keyList);
                    countDownLatch.countDown();
                });  //消费
        try{
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        consumer.close();
        Pair<Set<Object>, List<String>> pair = Pair.of(objectSet, keyList);
        //后续解析pair 即可
        return pair;
    }

    //config ConsumerConfig.MAX_POLL_RECORDS_CONFIG = 100
    public static int DEFAULT_READ_LENGTH = 100;

    ExecutorService threadPool = Executors.newCachedThreadPool(new ThreadFactory() {

        private volatile AtomicInteger threadNumber = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread( r, "kafka-get-message-" + threadNumber.getAndAdd(1));
        }
    });


    private void searchMessageFromPartition(QueueMessage message, PartitionInfo partitionInfo, boolean commit, Set<Object> objectSet, List<String> keyList){
        //get the length of the topic in one partition
        TopicPartition topicPartition = new TopicPartition(message.getTopic(), partitionInfo.partition());
        KafkaConsumer consumer = KafkaInstance.getInstance().createConsumer();
        consumer.assign(Arrays.asList(topicPartition));
        consumer.seekToEnd(Arrays.asList(topicPartition));
        //length: this partition length
        int messageLength = (int)consumer.position(new TopicPartition(message.getTopic(), partitionInfo.partition()));
        consumer.close();

        int divisionResult = (messageLength / DEFAULT_READ_LENGTH);
        int modResult = messageLength % DEFAULT_READ_LENGTH;
        int readCounts = divisionResult + (modResult > 0 ? 1 : 0);

        CountDownLatch countDownLatch = new CountDownLatch(readCounts);

        for(int i = 0 ; i < readCounts; i++){

            threadPool.execute(() -> {
                try {
                    KafkaConsumer consumer1 = KafkaInstance.getInstance().createConsumer();
                    consumer1.assign(Arrays.asList(topicPartition));
                    consumer1.seekToEnd(Arrays.asList(topicPartition));
                    ConsumerRecords records;
                    if (StringUtils.isNotBlank(getEnv("kafkaPoll"))) {
                        records = consumer1.poll(Duration.ofSeconds(Integer.valueOf(getEnv("kafkaPoll"))));
                    } else {
                        records = consumer1.poll(Duration.ofSeconds(5));
                    }
                    for (Object record : records) {
                        ConsumerRecord consumerRecord = (ConsumerRecord) record;
                        if (StringUtils.isNotBlank(message.getMessageId()) && StringUtils.equalsIgnoreCase(message.getMessageId(), String.valueOf(consumerRecord.key()))) {
                            objectSet.add(consumerRecord);
                            keyList.add(consumerRecord.key().toString());
                        }
                    }
                    if(commit){
                        for(Object o : objectSet){
                            ConsumerRecord consumerRecord =(ConsumerRecord)o;
                            consumer.commitSync(Collections.singletonMap(new TopicPartition(message.getTopic(), consumerRecord.partition()), new OffsetAndMetadata(consumerRecord.offset() + 1)));
                        }
                    }
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void testGroup(){
        KafkaConsumer consumer = KafkaInstance.getInstance().createConsumer();
    }

    private String getEnv(String key ){
        if(StringUtils.isNotBlank(System.getenv(key))){
            return System.getenv(key);
        }else if(StringUtils.isNotBlank(System.getProperty(key))){
            return System.getProperty(key);
        }
        return "";
    }

}
