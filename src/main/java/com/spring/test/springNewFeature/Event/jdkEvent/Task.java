package com.spring.test.springNewFeature.Event.jdkEvent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hlshi3
 * @date 2020/3/13 14:25
 */

//事件源
@Data
@AllArgsConstructor
public class Task {

    private String name;

    private TaskFinishStatus status;

}
