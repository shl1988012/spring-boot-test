package com.spring.test.algorithm;

import java.util.HashMap;
import java.util.Map;

//数组中出现次数超过一半的数字
public class Test29 {


    public static void main(String[] args) {

        int[] strs = {1,2,3,4,4,4,4,3,4,3,4,2,4};
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i<strs.length; i++){
            if(map.containsKey(strs[i])){
                int count = map.get(strs[i]);
                if(count++ >= strs.length/2){
                    System.out.println("获取超过一半次数的数字："+ strs[i]);
                }
                map.put(strs[i], count);
            }else{
                map.put(strs[i], 1);
            }

        }


    }


}
