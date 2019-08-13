package com.spring.test.springNewFeature.Condition;


import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(msgSenderConditionQQ.class)
public class ConditionTest {


}
