package com.spring.test.springNewFeature.Event.jdkEvent;

import java.util.EventObject;

/**
 * 任务结束事件
 * @author hlshi3
 * @date 2020/3/13 14:29
 */

public class TaskFinishEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TaskFinishEvent(Object source) {
        super(source);
    }


}
