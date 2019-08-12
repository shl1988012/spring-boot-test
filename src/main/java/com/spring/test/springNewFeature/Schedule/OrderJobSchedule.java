package com.spring.test.springNewFeature.Schedule;

import com.spring.test.springNewFeature.Event.HelloEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class OrderJobSchedule implements SchedulingConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> publish()
                 ,
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
//                    String cron = "*/5 * * * * ?";
                    String cron = "0 0 0 10,20,30 * ?";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

    //事件触发
    public void publish(){
        applicationContext.publishEvent(new HelloEvent(this, "shihengli"));
    }

}
