package com.spring.test.algorithm;

//10：二进制中1的个数
public class Test10 {



    public static Integer getOneNum(int n){

        if(n ==0){
            return 0;
        }
        int nums = 0;
        while(n !=0){
            n = n & (n-1);
            nums ++;
        }
        return nums;
    }


    public static void main(String[] args) {

        System.out.println(getOneNum(5));

    }
}
