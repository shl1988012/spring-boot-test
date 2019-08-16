package com.spring.test.jdkNewFeature.collections;

import java.util.*;

public class Test {


    public static void main(String[] args) {

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        Map map = Collections.synchronizedMap(new HashMap());



    }
}
