package com.spring.test.springNewFeature.Event.jdkEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义事件发布器
 * @author hlshi3
 * @date 2020/3/13 15:04
 */
public class TaskFinishEventPublisher {

    private List<TaskFinishListener> listners = new ArrayList<>();

    public synchronized void register(TaskFinishListener lister){
        if(!listners.contains(lister)){
            listners.add(lister);
        }
    }

    public synchronized  void remove(TaskFinishListener lister){
        listners.remove(listners);
    }

    public void publishEvent(TaskFinishEvent event){
        for (TaskFinishListener lis : listners){
            lis.onTaskFinish(event);
        }
    }


}
