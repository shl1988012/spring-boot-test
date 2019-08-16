package com.spring.test.springNewFeature.Condition;


import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(msgSenderConditionWECHAT.class)
public class ConditionTest {


    public void test(){
        System.out.println("========123456789===========");
    }

}
