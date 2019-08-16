package com.spring.test.kafka.message;

import com.spring.test.model.message.QueueMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
public class QueueMessageProducer {


    @Autowired
    @Qualifier("defaultKafkaTemplate")
    private KafkaTemplate kafkaTemplate;


    public void sendMessage(String topic, QueueMessage data){

        if(StringUtils.isBlank(topic)){
            topic = "topic.hengshi.test";
        }
        kafkaTemplate.send(topic, data).addCallback(new DemoProduceCallback());
    }


    public void sendBatchMessage(String topic, List<QueueMessage> message){

    }


    class DemoProduceCallback implements ListenableFutureCallback<SendResult<String, QueueMessage<String>>> {

        private  final Logger log = LoggerFactory.getLogger(DemoProduceCallback.class);

        @Override
        public void onFailure(Throwable ex) {
            log.error("send message error, e: {}", ex.getMessage());
        }

        @Override
        public void onSuccess(SendResult<String, QueueMessage<String>> result) {

            log.warn("send message success.");
        }
    }

}
