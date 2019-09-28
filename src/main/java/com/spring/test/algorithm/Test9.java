package com.spring.test.algorithm;

//斐波那契数列 f(n) = f(n-1) + f(n-2)
public class Test9 {

    public static int fibonacci(int n ){
        if(n<=0){
            return 0;
        }else if(n == 1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(2));
    }

}
