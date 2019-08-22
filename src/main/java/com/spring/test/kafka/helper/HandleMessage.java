package com.spring.test.kafka.helper;

import com.spring.test.kafka.helper.KafkaInstance.KafkaInstance;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class HandleMessage {


    public void handleMessage(FetchMessage message, boolean commit){

        KafkaInstance kafkaInstance = KafkaInstance.getInstance();
        KafkaConsumer consumer = kafkaInstance.createConsumer();

        List<PartitionInfo> partitions = (List<PartitionInfo>)consumer.listTopics().get(message.getTopic());
        if(partitions == null || partitions.isEmpty()){
            System.out.println("the topic is null, please check the topic name");
        }

        CountDownLatch countDownLatch = new CountDownLatch(partitions.size());
        Flux.fromIterable(partitions)
                .parallel(partitions.size())     //将任务分发到5个不同的线程进行处理
                .runOn(Schedulers.parallel())  // 将订阅者进行分轨
                .subscribe(partitionInfo -> {
                    //获取每个partition里面的message


                    countDownLatch.countDown();

                });  //消费
        try{
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        consumer.close();
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


    private void searchMessageFromPartition(FetchMessage message, PartitionInfo partitionInfo, boolean commit){
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

            threadPool.execute(() -> );


        }


    }




}
