package com.spring.test.springNewFeature.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class HelloEventLister2 {

    //事件处理程序
    @EventListener(HelloEvent.class)
    public void publish(HelloEvent event){

        log.info("receive {} say hello!",event.getName());
    }
}
