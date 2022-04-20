package com.spring.test.algorithm.niuke.字符串;

/**
 * 字符串变形
 *
 *  对于一个长度为 n 字符串，我们需要对它做一些变形。
 *
 * 首先这个字符串中包含着一些空格，就像"Hello World"一样，然后我们要做的是把这个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。
 *
 * 比如"Hello World"变形后就变成了"wORLD hELLO"。
 *
 * @author hlshi3
 * @date 2022/4/20 7:45
 */
public class BM83 {


    public String trans(String s, int n) {
        // write code here
        String[] strSplit = s.split(" ", -1);
        //反向遍历
        StringBuilder sb = new StringBuilder();
        for(int i =strSplit.length; i>=0;i-- ){
            //strSplit[i]进行反转
            sb.append(reverse(strSplit[i]));
            if(i==0){
                break;
            }
            sb.append(" ");
        }
        return sb.toString();
    }

    //进行大小写转换
    public String reverse(String s){
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(Character.isLowerCase(c)){
                sb.append(Character.toUpperCase(c));
            }else{
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

}
