package com.spring.test.springNewFeature.Event.springEvent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author hlshi3
 * @date 2020/3/13 17:14
 */
@Component
public class EventPublisher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //发布事件
    public void publishEvent(ApplicationEvent event){

        applicationContext.publishEvent(event);
    }

}
