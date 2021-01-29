package com.spring.test.springNewFeature.Event.jdkEvent;

import java.util.EventListener;

/** 抽象的事件监听器
 * @author hlshi3
 * @date 2020/3/13 14:46
 */
public interface TaskFinishListener extends EventListener {

    void onTaskFinish(TaskFinishEvent event);
}
