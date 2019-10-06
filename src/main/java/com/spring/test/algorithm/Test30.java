package com.spring.test.algorithm;

import java.util.ArrayList;
import java.util.List;

//输入n个整数，找出其中最小的k个数。
public class Test30 {


    public static List<Integer> getLeastNum(int[] nums, int k){
        List<Integer> list = new ArrayList<>();
        int maxNumInList = 0;
        for(int i =0; i<nums.length; i++){
            if(list.size()<k){
                list.add(nums[i]);
                if(maxNumInList< nums[i]){
                    maxNumInList = nums[i];
                }
            }else{
                if(nums[i]< maxNumInList){
                    list.remove((Integer)maxNumInList);
                    list.add(nums[i]);
                    //重新获取maxNumInList
                    maxNumInList = list.get(0);
                    for(int j = 0 ; j < list.size(); j ++){
                        if(maxNumInList<list.get(j)){
                            maxNumInList = list.get(j);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {

        int[] nums = {10,9,8,7,6,5,4,3,2,1};
        List<Integer> list = getLeastNum(nums, 5);
        list.forEach(integer -> System.out.println(integer));
    }
}
