package com.spring.test.algorithm.niuke.二分查找;

/**
 * 旋转数组的最小数字
 * 有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，变成一个旋转数组，比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。
 * 请问，给定这样一个旋转数组，求数组中的最小值。
 * @author hlshi3
 * @date 2022/4/17 9:50
 */
public class BM21 {

    // 1 0 1 1 1
    public int minNumberInRotateArray(int [] array) {
        int length = array.length;
        if(length ==0){
            return 0;
        }
        int left = 0, right =array.length-1;
        while(left < right){
            int mid = (left+right)>>1;
            // 中间的大于右边的，说明最小值在右边
            if(array[mid] > array[right]){
                left=mid+1;
            }else if(array[mid] < array[right]){ //中间值小于右边 说明最小值在左边
                right = mid;
            }else{
                //相等，则左移一位，重新计算mid索引
                right --;
            }
        }
        return array[left];
    }
}
