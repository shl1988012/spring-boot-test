package com.spring.test.annotation.demo2;

import com.spring.test.util.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class DictToCacheImpl {

    @Pointcut("execution(* com..*.service..*.*(..))")
    public void serviceAspect() {
    }


    @Around("serviceAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Object result = joinPoint.proceed();

        if(result != null){
             if(result instanceof List){

             }else{
                 result = this.processObject(result);
             }
        }
        return result ;
    }



    private Object processObject(Object result) {
        Object object = BeanUtils.code2MC(result);
        return object;
    }
}
