package com.spring.test.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {

    private static final Logger log = LoggerFactory.getLogger(TestLogController.class);

    @RequestMapping(path = "/testOffDebug", produces = {"application/json"}, method = {RequestMethod.GET})
    public void testLog() {
        System.out.println("user.dir = "+System.getProperty("user.dir"));
        if (log.isDebugEnabled()) {
            log.debug("this is debug log");
        }

        if (log.isInfoEnabled()) {
            log.info("this is info log");
        }
    }


}
