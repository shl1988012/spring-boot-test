package com.spring.test.algorithm.niuke.二分查找;

/**
 *  请实现无重复数字的升序数组的二分查找
 * 给定一个 元素升序的、无重复数字的整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标（下标从 0 开始），否则返回 -1
 * @author hlshi3
 * @date 2022/4/17 9:32
 */
public class BM17 {



    public int search (int[] nums, int target) {
        // write code here
        int length = nums.length;
        if(length == 0){
            return -1;
        }
        int left = 0 , right = length-1;
        while(left < right){
            int mid = (left+right)>>1;
            if(nums[mid] > target){
                right = mid -1;
            }else if(nums[mid] < target){
                left =mid +1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
