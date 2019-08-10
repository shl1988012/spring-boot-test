package com.spring.test.springNewFeature.Event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloEventListener implements ApplicationListener<HelloEvent> {

    //事件处理程序
    @Override
    public void onApplicationEvent(HelloEvent event) {
        log.info("receive {} say hello!",event.getName());
    }


}
