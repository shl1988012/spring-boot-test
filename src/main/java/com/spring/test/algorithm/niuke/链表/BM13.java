package com.spring.test.algorithm.niuke.链表;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 * @author hlshi3
 * @date 2022/4/17 9:00
 */
public class BM13 {


    /**
     * 给定一个链表，请判断该链表是否为回文结构。
     * 回文是指该字符串正序逆序完全一致。
     * @param head ListNode类 the head
     * @return bool布尔型
     */
    //栈先把链表的节点全部存放到栈中，然后再一个个出栈，这样就相当于链表从后往前访问了;
    //我们只需要拿链表的后半部分和前半部分比较即可，没必要全部比较
    public boolean isPail (ListNode head) {
        // write code here
        if(head == null) return true;
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        int length =0;
        while(temp != null){
            stack.push(temp.val);
            temp = temp.next;
            length++;
        }
        length>>=1; //比较一半
        while(length-->0){
            if(head.val != stack.pop()){
                return false;
            }else{
                head = head.next;
            }
        }
        return true;
    }
}
