package com.spring.test.algorithm.niuke.哈希;

import java.util.HashMap;

/**
 * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
 * @author hlshi3
 * @date 2022/4/19 7:41
 */
public class BM50 {


    /**
     * 暴力求解
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public int[] twoSum1 (int[] numbers, int target) {
        // write code here

        int[] result ={-1, -1};
        for(int i=0 ; i< numbers.length; i++){
            for(int j =i+1; j< numbers.length; j++){
                if(numbers[i] + numbers[j] == target){
                    result[0] = i+1;
                    result[1] = j+1;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public int[] twoSum2 (int[] numbers, int target) {
        // write code here

        int[] result ={-1, -1};
        HashMap<Integer, Integer> map = new HashMap<>(512);
        for(int i=0 ; i< numbers.length; i++){
            if(!map.containsKey(target-numbers[i])){
                map.put(numbers[i], i+1);
            }else{
                result[0] = map.get(target-numbers[i]);
                result[1] =  i+1;
                return result;
            }
        }
        return result;
    }

}
