package com.spring.test.algorithm.niuke.字符串;

/**
 *  反转字符串
 *
 *  写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
 * @author hlshi3
 * @date 2022/4/20 19:42
 */
public class BM91 {


    public String solve (String str) {
        // write code here
        if(str == null ){
            return null;
        }
        if(str.length() ==0 ){
            return "";
        }
        char[] chars = str.toCharArray();
        int p =0 ; int q= chars.length-1;
        for(; p<q; p++, q--){
            char temp = chars[p];
            chars[p] = chars[q];
            chars[q] = temp;
        }
        return new String(chars);
    }

}
