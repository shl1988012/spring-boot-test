package com.spring.test.algorithm;

import java.util.Stack;

//5：从尾到头打印链表
public class Test5 {


    public static  void reverseLinkedList(ListNode head){
        Stack s =new Stack();
        while(head != null){
            s.push(head.getValue());
            head = head.getNext();
        }
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(0);
        ListNode node_one = new ListNode(1);
        ListNode node_two = new ListNode(2);
        ListNode node_three = new ListNode(3);
        ListNode node_four = new ListNode(4);
        head.setNext(node_one);
        node_one.setNext(node_two);
        node_two.setNext(node_three);
        node_three.setNext(node_four);
        reverseLinkedList(head);
    }

}
