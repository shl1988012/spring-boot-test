package com.spring.test.annotation.demo;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SQLString {

    String name() default "";

    int value() default  0;

    //约束
    Constraints constraint() default @Constraints;
}
