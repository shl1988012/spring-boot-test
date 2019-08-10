package com.spring.test.init;

import com.spring.test.springNewFeature.Condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {


    @Bean
    @Conditional(msgSenderConditionQQ.class)
    MsgSender initMsgSenderQQ(){
        System.out.println("111111111111111111111111111111111");
        return new MsgSenderQQ();
    }

    @Bean
    @Conditional(msgSenderConditionWECHAT.class)
    MsgSender initMsgSenderWechat(){
        System.out.println("22222222222222222222222222222222");
        return new MsgSenderWebchat();
    }


}
