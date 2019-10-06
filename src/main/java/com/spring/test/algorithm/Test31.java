package com.spring.test.algorithm;

//连续字数组的最大和
//输入一个整型数组，数组里有正数也有负数。数组中一个或者连续的多个整数组成一个字数组。求所有字数组的和的最大值。要求时间复杂度为O(n)
//时间复杂度为O(n)，则只能遍历一次数组
public class Test31 {


    public static int findGreatSumOfSubArray(int array[]){
        int maxSum = 0;
        int currentSum = 0;

        for (int i = 0; i < array.length; i++){
            currentSum+=array[i];
            if(currentSum > 0){
                if(currentSum> maxSum){
                    maxSum = currentSum;
                }
            }else{
                currentSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int array[] ={-1, -3, 1, -1, 5, -2, 3, -2};
        System.out.println(findGreatSumOfSubArray(array));
    }


}
