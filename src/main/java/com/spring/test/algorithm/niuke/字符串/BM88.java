package com.spring.test.algorithm.niuke.字符串;

/**
 * 判断是否为回文字符串
 * @author hlshi3
 * @date 2022/4/20 8:32
 */
public class BM88 {


    public boolean judge (String str) {
        // write code here
        if(str == null || str ==""){
            return true;
        }
        char[] chars = str.toCharArray();
        int p=0; int q = chars.length-1;

        for(; p<=q;p++,q--){
            if(chars[p] != chars[q]){
                return false;
            }
        }
        return true;
    }

}
