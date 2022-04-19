package com.spring.test.algorithm.niuke.哈希;

import java.util.HashMap;

/**
 * BM51 数组中出现次数超过一半的数字
 * @author hlshi3
 * @date 2022/4/19 8:27
 */
public class BM51 {


    public int MoreThanHalfNum_Solution(int [] array) {
        int len = array.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0 ; i<len-1; i++){
            map.put(i, map.getOrDefault(i,0)+1);
            if(map.get(i) > len>>1){
                return i;
            }
        }
        return -1;
    }


}
