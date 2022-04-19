package com.spring.test.algorithm.niuke.堆栈队列;

import java.util.Stack;

/**
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 * @author hlshi3
 * @date 2022/4/19 7:29
 */
public class BM44 {


    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isValid (String s) {
        if(null == s){
            return true;
        }
        Stack<Character> tempStack = new Stack<>();
        for(char item : s.toCharArray()){
            if(item == '('){
                tempStack.push(')');
            }else if(item == '{'){
                tempStack.push('}');
            }else if(item == '['){
                tempStack.push(']');
            }else{
                if(tempStack.isEmpty() || item!= tempStack.peek()){
                    return false;
                }
                tempStack.pop();
            }
        }
        return tempStack.isEmpty()? true : false;
    }







}
