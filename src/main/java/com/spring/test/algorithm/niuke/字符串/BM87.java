package com.spring.test.algorithm.niuke.字符串;

/**
 * 合并两个有序的数组
 *
 * 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
 *
 * @author hlshi3
 * @date 2022/4/20 8:23
 */
public class BM87 {

    //使用双指针方式，新增一个数组，每次保存较小的数值。
    public void merge(int A[], int m, int B[], int n) {
        int p=0, q=0;
        int[] sorted = new int[m+n];
        int cur;
        while(p <m || q <n){
            if(p == m){
                cur = B[q++];
            }else if(q == n){
                cur = A[p++];
            }else if(A[p]> B[q]){
                cur = B[q++];
            }else{
                cur = A[p++];
            }
            sorted[p+q-1] = cur;
        }

        //将sorted->A
        for(int i=0; i<m+n; i++){
            A[i] = sorted[i];
        }
    }
}
