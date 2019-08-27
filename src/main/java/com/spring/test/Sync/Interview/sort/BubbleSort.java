package com.spring.test.Sync.Interview.sort;

import java.util.Arrays;

public class BubbleSort {


    public void bubble(int [] arr){

        for(int i = arr.length-1; i>0; i--){
            for(int j =0; j<i ; j++){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                System.out.println("Sorting: " + Arrays.toString(arr));

            }

        }


    }

}
