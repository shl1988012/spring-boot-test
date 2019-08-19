package com.spring.test.Sync.Interview;


//如果字符串s的第一个字符等于最后一个字符，而第二个字符等于倒数第二个字符。
//        以此类推，那么该字符串就是一个回文字符串。“目前有一个字符串S，
//        请从S中找到最长回文字符串，即要找到一个最长子串，同时该子串是一个回文子串。”（如果有多个可选择输出其中任意一个）
public class Test4 {


    public static int length = 0;
    public static String result = "";

    public static boolean checkStr(String str){
        char[] strToChars = str.toCharArray();
        int len =str.length();
        for(int i = 0; i< len/2; i++ ){
            if(strToChars[i] != strToChars[len-i-1]){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        String str = "abccbacccabcdefgfedcba";

        for(int i = 0; i< str.length(); i++ ){
            for(int j = i+1; j<= str.length(); j++){
                String subStr = str.substring(i, j);
                if(checkStr(subStr)){
                    if(length < subStr.length()){
                        length = subStr.length();
                        result = subStr;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
