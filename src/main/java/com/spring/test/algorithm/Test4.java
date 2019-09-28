package com.spring.test.algorithm;


//实现一个函数，把字符串中的每个空格替换成"%20"。
public class Test4 {

    public static String replaceBlank(String str){

        char[] c_old = str.toCharArray();
        int blankNum = 0;
        for(char c : c_old){
            if(' ' == c){
                blankNum++;
            }
        }

        int newCharLength = c_old.length + blankNum*2;
        char[] newChar = new char[newCharLength];
        int k = newCharLength-1;
        for(int i = c_old.length-1 ; i >=0 ; i--){
            if(c_old[i] == ' ' ){
                newChar[k--] ='0';
                newChar[k--] ='2';
                newChar[k--] ='%';
            }else{
                newChar[k] = c_old[i] ;
            }
            k --;
        }
        return new String(newChar);

    }

    public static void main(String[] args) {
        String str = " ";
        System.out.println(replaceBlank(str));


//        char[] chars = new char[3];
//        chars[2]='0';
//        chars[1]='2';
//        chars[0]='%';
//        System.out.println(new String(chars));
    }
}
