package com.spring.test.algorithm.niuke.字符串;

/**
 * BM84 最长公共前缀
 *
 * 给你一个大小为 n 的字符串数组 strs ，其中包含n个字符串 , 编写一个函数来查找字符串数组中的最长公共前缀，返回这个公共前缀。
 *
 * @author hlshi3
 * @date 2022/4/20 8:08
 */
public class BM84 {

    /**
     * 依次比较每个字节是否相同
     * @param strs
     * @return
     */
    public String longestCommonPrefix (String[] strs) {
        // write code here

        for(int i =0; i< strs[0].length(); i++){
            int firstStrChar = strs[0].charAt(i); //第一个字符串的字节
            for(int j=1; j< strs.length; j++){
                //比较每个字符串的相同位置的字节 是否相等
                if(strs[j].length() ==i || strs[j].charAt(i) != firstStrChar){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
