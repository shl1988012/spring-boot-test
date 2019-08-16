package com.spring.test.kafka.message;

import com.spring.test.model.message.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class QueueMessageConsumer implements ConsumerMessage<ConsumerRecord<String, QueueMessage<String>>, Acknowledgment>{

    @Autowired
    @Qualifier("defaultKafkaTemplate")
    private KafkaTemplate kafkaTemplate;


    @Override
    @KafkaListener(id ="queueMessage", topics = "topic.hengshi.test", containerFactory="ackContainerFactory")
    public void receiveMessage(List<ConsumerRecord<String, QueueMessage<String>>> records, Acknowledgment ack) throws Exception {
        log.info("start to consumer queue message with ack. records.size :{}", records.size());
        Flux.fromIterable(records).parallel(records.size()).runOn(Schedulers.parallel()).subscribe(record ->{
            try{
                int a = 1/0;

            }catch(Exception e){
                log.error("consumer message error. will send the record into retry-topic.");
                kafkaTemplate.send(record.topic()+"-retry", record.value());
            }

        });
    }

    @Override
    public void receiveMessage(ConsumerRecord<String, QueueMessage<String>> record, Acknowledgment ack) throws Exception {

    }

    @Override
    @KafkaListener(id ="queueMessage-retry", topics = "topic.hengshi.test-retry", containerFactory="ackContainerFactory")
    public void receiveRetryMessage(List<ConsumerRecord<String, QueueMessage<String>>> records, Acknowledgment ack) throws Exception {

        log.info("start to consumer queue message with ack in retry topic. records.size :{}", records.size());
        Flux.fromIterable(records).parallel(records.size()).runOn(Schedulers.parallel()).subscribe(record ->{
            try{
                QueueMessage queueMessage = record.value();
                log.info("the retry time is:{}", queueMessage.getRetryTimes());
                receiveRetryQueueMessage(record, ack);
            }catch(Exception e){
                if(record.value().getRetryTimes() < 3){
                    QueueMessage<String> message = record.value();
                    message.setRetryTimes(message.getRetryTimes()+1);
                    kafkaTemplate.send(record.topic(), message);
                    return ;
                }
                //send to dead queue
                sendtoDeadQueue(record);
            }finally {
                ack.acknowledge();
            }
        });

    }

    private void receiveRetryQueueMessage(ConsumerRecord<String, QueueMessage<String>> record, Acknowledgment ack) throws Exception{

        QueueMessage<String> message = record.value();
        // set retry wait time
        long maxWaitTime = 120; // this can be configed
        //The greater the number of retries, the longer the waiting time.
        long waitTime = getWaitTimes(message.getTimeStamp(), message.getRetryTimes());
        waitTime = waitTime < maxWaitTime ? waitTime : maxWaitTime;
        if(waitTime < 0){
            log.info(" the record:{} is failed in times: {}",record,  message.getRetryTimes());
        }
        TimeUnit.SECONDS.sleep(waitTime);

        consumerRetryMessage(record, ack);
    }


    private void consumerRetryMessage(ConsumerRecord<String, QueueMessage<String>> record, Acknowledgment ack){

        log.info("start to consumer message in retry topic. retry times:{}", record.value().getRetryTimes());

        int a = 1/0;

    }

    private long getWaitTimes(long failedTime ,int retryTimes){
        //use kafkaConstant.java
//        int internalTime = 10;
//        int multiplierTime = 5;
//        int randomMultiplierTime = RandomUtils.nextInt(1, multiplierTime);
//        long now = Instant.now().getEpochSecond();
//        return  (internalTime * randomMultiplierTime - (now - failedTime)) * retryTimes ;
        return 5;
    }

    private void sendtoDeadQueue(ConsumerRecord<String, QueueMessage<String>> record) {
        kafkaTemplate.send("topic.hengshi.test-deadQueue", record.value());
    }

    @Override
    public void receiveRetryMessage(ConsumerRecord<String, QueueMessage<String>> record, Acknowledgment ack) throws Exception {

    }


    public static void main(String[] args) {

        System.out.println(Instant.now().getEpochSecond());
    }
}
