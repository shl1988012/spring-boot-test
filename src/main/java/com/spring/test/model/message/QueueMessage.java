package com.spring.test.model.message;

import lombok.Data;

@Data
public class QueueMessage<T> {

    private String id;

    private int retryTimes;

    private long timeStamp;

    private T message;
}
