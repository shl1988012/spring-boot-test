package com.spring.test.kafka.helper.KafkaInstance;

import com.spring.test.kafka.Serializer.ObjectDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.HashMap;
import java.util.Map;

public class KafkaInstance {

    private KafkaInstance(){}

    private static volatile KafkaInstance kafkaInstance;

    public static  KafkaInstance getInstance(){

        if(kafkaInstance == null){
            synchronized (KafkaInstance.class){
                if(kafkaInstance == null){
                    kafkaInstance =new KafkaInstance();
                }
            }
        }
        return kafkaInstance;
    }


    //消费者配置
    public static Map<String, Object> consumerProps(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://sjc-coi01-lnx:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
        //earliest: automatically reset the offset to the earliest offset
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class);

        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class);

        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,100);
        return props;
    }

    public KafkaConsumer createConsumer(){

        KafkaConsumer<Object, Object> consumer = new KafkaConsumer(consumerProps());
        return consumer;
    }





}
