package com.spring.test.model.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueueMessage<T> implements Serializable {

    private String id;

    private int retryTimes;

    private long timeStamp;

    private T message;
}
