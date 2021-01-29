package com.spring.test.algorithm.jike.course11;

import java.util.Arrays;

/**
 * @author hlshi3
 * @date 2020/11/23 16:46
 */
public class BubbleSort {

    /**
     * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。如果不满足就让它俩互换。
     * 一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作
     * @param a
     * @param n
     */
    public static void bubbleSort(int[]a, int n){
        if(n<=1){
            return;
        }
        for(int i = 0; i<n; i++){
            boolean flag = false;
            for(int j= 0; j<n-i-1; j++ ){
                if(a[j]> a[j+1]){
                    //交换
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }


    public static void insertSort(int[]a, int n){
        if(n<=1){
            return;
        }
        for(int i =1 ; i< n ; i++){
            int value = a[i];
            int j = i-1;
            for(; j>=0; j--){  //j循环已排序的部分列表
                if(a[j] > value){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = value;
        }
    }


    public static void main(String[] args) {

        int[]a ={4,5,6,3,2,1};
//        bubbleSort(a, 6);

        insertSort(a, 6);



        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

    }

}
