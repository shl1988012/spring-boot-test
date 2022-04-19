package com.spring.test.algorithm.niuke.动态规划;

/**
 *  一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 数据范围：1≤n≤40
 *
 * @author hlshi3
 * @date 2022/4/19 8:48
 */
public class BM63 {

    public int jumpFloor(int target) {
        if(target ==1){
            return 1;
        }
        if(target == 2){
            return 2;
        }
        return jumpFloor(target-1) + jumpFloor(target-2);
    }

}
