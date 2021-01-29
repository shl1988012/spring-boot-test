package com.spring.test.springNewFeature.Event.jdkEvent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 邮件服务监听器
 * @author hlshi3
 * @date 2020/3/13 14:49
 */
@Data
@AllArgsConstructor
public class MailTaskFinishListener implements TaskFinishListener{

    private String email;

    @Override
    public void onTaskFinish(TaskFinishEvent event) {
        System.out.println("Send Emial to "+ email +" Task:"+ event.getSource());
    }

}
