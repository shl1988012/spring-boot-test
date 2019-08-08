package com.spring.test.springNewFeature.Condition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class msgSenderConditionWECHAT implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        String wechat = conditionContext.getEnvironment().getProperty("msg.sender");
        return StringUtils.equalsIgnoreCase(wechat,"weixin");
    }
}
