package com.spring.test.kafka.helper;

import lombok.Data;

@Data
public class FetchMessage {
//    KAFKA_HELP_MESSAGE_FETCH("kh -message fetch ", "^(kh -message fetch)(\\s*(\\w+-*)+)(\\s*.+)$", "fetch messages by id or time or offset index. Should use like:\n  kh -message fetch topicName messageId;\n  kh -message fetch topicName 2019-01-01 01:01:01 2019-02-01 01:01:01;\n  kh -message fetch topicName partitionNo startOffset endOffset;\n"),
    private String topic;
    private String messageId;
    private String startTime;
    private String endTime;
    private String startOffset;
    private String endOffset;
    private int partition;


}
