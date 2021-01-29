package com.spring.test.springNewFeature.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.spring.test.springNewFeature.UserDao.addUser(..))")
    private void myPointCut(){

    }
    /**
     * 前置通知
     */
    @Before("myPointCut()")
    public void before(){
        System.out.println("前置通知....");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="myPointCut()",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }


    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前....");
        Object obj= (Object) joinPoint.proceed();
        //获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName: "+ methodName);

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        System.out.println("targetMethod: "+ targetMethod.getName());
        System.out.println("环绕通知后....");
        return obj;
    }


    @AfterThrowing(value="myPointCut())",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("出现异常:msg="+e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value="myPointCut()")
    public void after(){
        System.out.println("最终通知....");
    }



}
