package com.spring.test.annotation.demo1;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAlarm {


    @Pointcut("@annotation(alarm)")
    public void alarmService(Alarm alarm){

    }

    @Pointcut("execution(* *(..))")
    public void atExecution(){

    }

//    @Before("alarmService() && atExecution()")
//    public void inspect(){
//        System.out.println("aaaaaaaaaaa");
//    }


//    @Around("alarmService(alarm) && atExecution()")
//    public void aroundInspect(ProceedingJoinPoint thisJoinPoint, Alarm alarm){
//
//        System.out.println("aaaaaaaaaaa");
//        try {
//            thisJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println("bbbbbbbbbb");
//    }


    @AfterReturning(returning ="responseObj",value = "alarmService(alarm) && atExecution()" )
    public void afterInspect(Object responseObj, Alarm alarm){

        String response  = (String)responseObj;
        System.out.println("============="+ response);
    }

}
