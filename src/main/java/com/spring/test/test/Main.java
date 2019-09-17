package com.spring.test.test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        Map<String,String> resourceCaches = new ConcurrentHashMap<>(4);
        resourceCaches.computeIfAbsent("1",s -> "2");
        resourceCaches.put("2", "3");
        resourceCaches.forEach((k, v) -> System.out.println(k+":"+v) );
//        resourceCaches.clear();

        List list = resourceCaches.entrySet().stream().collect(Collectors.toList());

        list.stream().forEach(System.out::println);


//        ClassPathResource classPathResource = new ClassPathResource("");
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions(classPathResource);

        ReentrantLock lock =new ReentrantLock();
        lock.tryLock();
        synchronized (Main.class){
            System.out.println("");
            synchronized (Main.class){
                System.out.println("");
            }
        }
    }
}
