package com.spring.test.springNewFeature.Event.springEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author hlshi3
 * @date 2020/3/13 17:12
 */
@Component
public class MailTaskFinishListener2 implements ApplicationListener<TaskFinishEvent2> {

    private String emial="takumiCX@163.com";

    @Override
    public void onApplicationEvent(TaskFinishEvent2 event) {
        System.out.println("Send Emial to "+emial+" Task:"+event.getSource());
    }
}
