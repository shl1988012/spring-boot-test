package com.spring.test.controller;


import com.spring.test.annotation.demo1.Alarm;
import com.spring.test.model.SynStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "register")
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

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return "当前端口："+this.port;
    }


    @PostMapping(value = "/synStatus")
    public String testMap(@RequestParam Map<String, Object> body){
        body.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
        return "";
    }


}
