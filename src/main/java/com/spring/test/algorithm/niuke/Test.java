package com.spring.test.algorithm.niuke;

/**
 * @author hlshi3
 * @date 2022/4/29 19:04
 */
public class Test {


    public static void main(String[] args) {
        int [] arr = {4,5,6,7,0,1,2};
        for(int index = 0; index<arr.length ; index++){
            System.out.println(getIndex(arr, arr[index]));
        }

    }


    public static int getIndex(int[] arr, int value){
        if(arr.length ==0){
            return -1;
        }
        int leftIndex =0;
        int rightIndex = arr.length-1;

        while(leftIndex <= rightIndex){
            int midIndex = (leftIndex+rightIndex)/2;
            if(arr[midIndex] == value){
                return midIndex+1;
            }
            //左侧<中间  如果value>mid 在mid右侧 ；value < mid && value >left  在mid左侧；value<left,在mid右侧
            if(arr[leftIndex] <= arr[midIndex]){
                if(value < arr[midIndex] && value >= arr[leftIndex]){
                    rightIndex =midIndex-1;
                }else{
                    leftIndex = midIndex+1;
                }
            }else{
                //leftIndex > mid 说明mid在b数组中。
                //如果value> left 那么right=mid-1;  value<left&& value>mid 那么 left=mid+1； value<left &&value<mid 那么right =mid-1
                if( value> arr[midIndex] && value <= arr[rightIndex]){
                    leftIndex = midIndex+1;
                }else{
                    rightIndex =midIndex-1;
                }
            }
        }
        return -1;
    }

  

}
