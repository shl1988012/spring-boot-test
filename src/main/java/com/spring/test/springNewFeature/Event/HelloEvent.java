package com.spring.test.springNewFeature.Event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

//事件源
@Data
public class HelloEvent extends ApplicationEvent {

    private String name;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public HelloEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

}
