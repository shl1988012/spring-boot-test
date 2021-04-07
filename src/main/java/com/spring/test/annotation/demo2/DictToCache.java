package com.spring.test.annotation.demo2;

public @interface DictToCache {

    String fieldName() default "";

    String dataType() default "";

//    String emptyText() default "";

}
