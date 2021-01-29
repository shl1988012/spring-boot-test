package com.spring.test.springNewFeature.Event.springEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @author hlshi3
 * @date 2020/3/13 17:11
 */
public class TaskFinishEvent2 extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TaskFinishEvent2(Object source) {
        super(source);
    }
}
