package com.spring.test.algorithm;

//14：调整数组顺序使奇数位于偶数前面
public class Test14 {

    public static void reverse(int[] arrs){
        int offset = arrs.length;
        for(int i = 0; i < offset; ){
            if(arrs[i] % 2 == 0) { //偶数
                if(arrs[offset-1] % 2 !=0){ //奇数
                    int temp = arrs[offset-1];
                    arrs[offset-1] = arrs[i];
                    arrs[i] = temp;
                }else{
                    offset -- ;
                }
            }else{
                i++;
            }
        }
    }

    public static void main(String[] args) {

        int[] arrs = {4,4,4,4};
        reverse(arrs);
        for(int i =0 ; i< arrs.length ; i++){
            System.out.println(arrs[i]);

        }
    }
}
