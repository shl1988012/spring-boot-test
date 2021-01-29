package com.spring.test.kafka.config;


import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaInitialConfiguration {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>(4);
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "http://172.31.129.156:9092");
        return new KafkaAdmin(configs);
    }


    @Bean
    public AdminClient adminClient(){
        return AdminClient.create(kafkaAdmin().getConfig());
    }

    //创建TopicName为topic.hengshi.test的Topic并设置分区数为5以及副本数为1
    //副本数和pod 一致
    @Bean
    public NewTopic initialTopic(){
        return new NewTopic("topic.hengshi.test", 5, NumberUtils.toShort("1"));
    }

//    @Bean
//    public NewTopic batchTopic(){
//        return new NewTopic("topic.hengshi.batch", 8, (short)1);
//    }



}
