package com.spring.test.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AnnotationElementDemo {

    enum Status {FIXED, NORMAL};

    Status status() default Status.FIXED;

    boolean showSupport() default false;

    String name() default "";

    Class<?> testCase() default Void.class;

    //注解嵌套
    Reference reference() default @Reference(next = true);

    long[] value();

}
