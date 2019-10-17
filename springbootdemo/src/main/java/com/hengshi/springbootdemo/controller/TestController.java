package com.hengshi.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(path = "/memoryLeak", produces = {"application/json"}, method = {RequestMethod.GET})
    public void memoryLeak(){
        for(int i=0; i<1000000000; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    @RequestMapping(path = "/testShield", produces = {"application/json"}, method = {RequestMethod.GET})
//    public String testShield(){
//        Object o = HttpUtils.httpGet("http://10.4.149.114:4989/asd",1000,1000);
//        return o.toString();
//    }

}
