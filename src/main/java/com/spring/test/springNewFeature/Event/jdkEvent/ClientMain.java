package com.spring.test.springNewFeature.Event.jdkEvent;

/**
 * @author hlshi3
 * @date 2020/3/13 15:08
 */
public class ClientMain {


    public static void main(String[] args) {
        Task source = new Task("用户统计", TaskFinishStatus.SUCCESS);
        TaskFinishEvent event = new TaskFinishEvent(source);
        MailTaskFinishListener listener = new MailTaskFinishListener("aaa@163.com");
        TaskFinishEventPublisher publisher = new TaskFinishEventPublisher();
        publisher.register(listener);
        publisher.publishEvent(event);
    }

}
