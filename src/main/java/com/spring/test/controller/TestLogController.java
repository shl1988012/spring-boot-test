package com.spring.test.controller;


import com.spring.test.annotation.demo1.Alarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {

    private static final Logger log = LoggerFactory.getLogger(TestLogController.class);

    @Alarm
    @RequestMapping(path = "/testOffDebug", produces = {"application/json"}, method = {RequestMethod.GET})
    public String testLog() {
        System.out.println("user.dir = "+System.getProperty("user.dir"));
        if (log.isDebugEnabled()) {
            log.debug("this is debug log");
        }

        if (log.isInfoEnabled()) {
            log.info("this is info log");
        }
        return "abc";
    }


}
