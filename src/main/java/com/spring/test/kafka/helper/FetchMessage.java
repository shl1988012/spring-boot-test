package com.spring.test.kafka.helper;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Data
public class FetchMessage {
//    KAFKA_HELP_MESSAGE_FETCH("kh -message fetch ", "^(kh -message fetch)(\\s*(\\w+-*)+)(\\s*.+)$", "fetch messages by id or time or offset index. Should use like:\n  kh -message fetch topicName messageId;\n  kh -message fetch topicName 2019-01-01 01:01:01 2019-02-01 01:01:01;\n  kh -message fetch topicName partitionNo startOffset endOffset;\n"),
    private String topic;
    private String messageId;
    private long startTime;
    private long endTime;
    private String startOffset;
    private String endOffset;
    private int partition;

    //search Type: id, time, offset

    public FetchMessage(){}

    public FetchMessage(String startTime, String endTime){
        Instant start = null;
        Instant end = null;
        try {
            start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime).toInstant();
            end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime).toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(start != null && end != null){
            this.startTime = start.toEpochMilli();
            this.endTime = end.toEpochMilli();
        }
    }

}
