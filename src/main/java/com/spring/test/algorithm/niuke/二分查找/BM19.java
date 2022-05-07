package com.spring.test.algorithm.niuke.二分查找;

/**
 * 寻找峰值
 * 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
 * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
 * 2.假设 nums[-1] = nums[n] = −∞-\infty−∞
 * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
 *
 *
 * 由于题目假设nums[-1]和nums[n]等于负无穷，也就是说，nums[0]>nums[-1]，那么只要nums[0]>nums[1]，nums[0]就是一个峰值。
 *
 * 否则，说明nums[1]>nums[0]（题目设定了nums[i]不等于nums[i+1]），那么只要nums[1]>nums[2]，nums[1]就是一个峰值。
 * 根据上面发现的规律，可以再深入一步，得出两个结论：
 *
 * ①若nums[i]>nums[i+1]，那么在[0,i]区间内一定存在峰值。
 *
 * ②若nums[i]<nums[i+1]，那么在[i+1,n-1]区间内一定存在峰值。
 * @author hlshi3
 * @date 2022/5/1 9:05
 */
public class BM19 {


    public int findPeakElement (int[] nums) {
        // write code here
        int leftIndex =0;
        int rightIndex =nums.length-1;
        //注意！！！  leftIndex不能等于rightIndex。 当二分法最后只剩下一个值的时候，leftIndex=rightIndex,再次进入while循环 midIndex=leftIndex=rightIndex,这时候midIndex+1就会出现outOufIndexException
        while(leftIndex < rightIndex){
            int midIndex =(leftIndex+ rightIndex)/2;
            if(nums[midIndex] < nums[midIndex+1]){
                leftIndex =midIndex+1;  //必须+1，不然会出现死循环。该题是为了获取峰值，只要那个峰值在区间即可。所以index=midIndex+1
            }else{
                rightIndex =midIndex;
            }
        }
         return leftIndex;
    }
}
