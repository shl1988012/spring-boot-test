package com.spring.test.controller;

//import com.spring.test.init.TestInit;
import com.spring.test.springNewFeature.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAopController {

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private TestInit testInit;

    @RequestMapping(path = "/testAop", produces = {"application/json"}, method = {RequestMethod.GET})
    public int testAop(){
       return  userDao.addUser();
    }


//    @RequestMapping(path = "/testInit", produces = {"application/json"}, method = {RequestMethod.GET})
//    public Object testInit(){
//        if(testInit.containBean("userDao")){
//            UserDao userDao =(UserDao)testInit.getBean("userDao");
//            System.out.println(userDao.addUser());
//        }
//      return null;
//    }

}
