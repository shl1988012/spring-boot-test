package com.spring.test.springNewFeature.aop;


import com.spring.test.model.MonitorTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {

    @Pointcut("execution(* com.spring.test.controller.AnalyseFileController.*(..))")
    public void myPointCut(){

    }

    @Around("myPointCut()")
    public Object longTime(ProceedingJoinPoint joinPoint){
        MonitorTime monitorTime = new MonitorTime();
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println("className:" + className);

        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName:" + methodName);

        long startTime = System.currentTimeMillis();
        Object result ="";
        try {
            result  = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("consumer time : "+ time);
        monitorTime.setClassName(className);
        monitorTime.setMethodName(methodName);
        monitorTime.setConsumerTime(time);
        return result ;
    }


}
