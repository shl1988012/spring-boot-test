package com.spring.test.controller;


import com.spring.test.kafka.config.KafkaSendResultHandler;
import com.spring.test.kafka.message.QueueMessageProducer;
import com.spring.test.model.message.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KafkaController {


    @Autowired
    @Qualifier("defaultKafkaTemplate")
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private KafkaSendResultHandler resultHandler;

    @Autowired
    private QueueMessageProducer queueMessageProducer;


    @RequestMapping(path = "/testKafkaDemo", produces = {"application/json"}, method = {RequestMethod.GET})
    public void testDemo() throws InterruptedException {
        kafkaTemplate.send("topic.hengshi.test", "this is my first demo");
        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
        Thread.sleep(1000);
    }

    @RequestMapping(path = "/createTopic", produces = {"application/json"}, method = {RequestMethod.GET})
    public void createTopic() throws InterruptedException {
        adminClient.createTopics(Arrays.asList(new NewTopic("topic.hengshi.test", 1,(short)1)));
    }


    @RequestMapping(path = "/topicInfo", produces = {"application/json"}, method = {RequestMethod.GET})
    public void topicInfo() throws Exception {
        DescribeTopicsResult topicsResult = adminClient.describeTopics(Arrays.asList("topic.hengshi.test"));
        topicsResult.all().get().forEach((k,v)->System.out.println("k: "+k+" ,v: "+v.toString()+"\n"));
    }

    @RequestMapping(path = "/sendToDefaultTemplate", produces = {"application/json"}, method = {RequestMethod.GET})
    public void sendToDefaultTemplate() throws Exception {

        //使用ProducerRecord发送消息
//        ProducerRecord record = new ProducerRecord("topic.hengshi.test", "use ProducerRecord to send message");
//        kafkaTemplate.send(record).addCallback(new DemoProduceCallback());

        //使用message发送消息
//        Map map = new HashMap();
//        map.put(KafkaHeaders.TOPIC, "topic.hengshi.test");
//        map.put(KafkaHeaders.PARTITION_ID, 3);
//        map.put(KafkaHeaders.MESSAGE_KEY, 0);
//        GenericMessage message = new GenericMessage("use Message to send message",new MessageHeaders(map));
//        kafkaTemplate.send(message);

        QueueMessage<String> message = new QueueMessage<>();

        message.setRetryTimes(0);
        message.setId("111");
        message.setMessage("aaaaaaaaaaaaaa");
        message.setTimeStamp(Instant.now().getEpochSecond());
        queueMessageProducer.sendMessage("topic.hengshi.test", message);
    }

    @RequestMapping(path = "/testProducerListen", produces = {"application/json"}, method = {RequestMethod.GET})
    public void testProducerListen() throws Exception {

        kafkaTemplate.setProducerListener(resultHandler);
        kafkaTemplate.send("topic.hengshi.test", 4, System.currentTimeMillis(), 0,"send message with timestamp");

    }


}
