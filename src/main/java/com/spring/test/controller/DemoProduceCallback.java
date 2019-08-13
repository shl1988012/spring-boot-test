package com.spring.test.controller;

import com.spring.test.model.message.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class DemoProduceCallback implements ListenableFutureCallback<SendResult<String, QueueMessage<String>>> {


    @Override
    public void onFailure(Throwable ex) {
       log.error("send message error, e: {}", ex.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, QueueMessage<String>> result) {
        log.warn("send message success.");
    }
}
