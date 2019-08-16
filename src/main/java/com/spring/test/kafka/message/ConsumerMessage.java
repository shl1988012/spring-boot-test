package com.spring.test.kafka.message;

import com.spring.test.model.message.QueueMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

public interface ConsumerMessage<T,V> {

    public  void receiveMessage(List<T> records, V ack) throws Exception;

    public  void receiveMessage(T record, V ack) throws Exception;

    public  void receiveRetryMessage(List<T> records, V ack) throws Exception;

    public void receiveRetryMessage(ConsumerRecord<String, QueueMessage<String>> record, Acknowledgment ack) throws Exception;
}
