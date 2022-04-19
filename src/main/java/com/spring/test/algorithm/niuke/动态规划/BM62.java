package com.spring.test.algorithm.niuke.动态规划;

/**斐波那契数列
 * @author hlshi3
 * @date 2022/4/19 8:44
 */
public class BM62 {

    public int Fibonacci(int n) {
        if(n==1 || n ==2){
            return 1;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }
}

