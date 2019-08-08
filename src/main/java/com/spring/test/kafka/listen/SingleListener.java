package com.spring.test.kafka.listen;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SingleListener {

    @KafkaListener(id ="consumer", topics = {"topic.hengshi.test"})
    public void consumerListener(ConsumerRecord<Integer, String> record){
        log.info("topic.quick.consumer receive : "+ record.toString());

    }


}
