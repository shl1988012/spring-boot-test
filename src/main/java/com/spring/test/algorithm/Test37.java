package com.spring.test.algorithm;

import java.util.Stack;

//两个链表的第一个公共结点
public class Test37 {

    public static ListNode findFirst(ListNode head1, ListNode head2){

        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while(head1 !=null){
            stack1.push(head1);
            head1 = head1.getNext();
        }

        while(head2 !=null){
            stack2.push(head2);
            head2 = head2.getNext();
        }

        ListNode nNext = null;

        while(!stack1.isEmpty() && !stack2.isEmpty()){
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            if(node1 == node2){
                nNext = node1;
            }else{
                break;
            }
        }
        return nNext;
    }


    public static void main(String[] args) {
        // 构建链表
        ListNode head1 = new ListNode(1);
        ListNode node_2 = new ListNode(2);
        ListNode node_3 = new ListNode(3);
        ListNode head2 = new ListNode(4);
        ListNode node_5 = new ListNode(5);
        ListNode node_6 = new ListNode(6);
        ListNode node_7 = new ListNode(7);
        head1.setNext(node_2);
        node_2.setNext(node_3);
        node_3.setNext(node_6);
        node_6.setNext(node_7);
        node_7.setNext(null);
        head2.setNext(node_5);
        node_5.setNext(node_6);

        ListNode result = findFirst(head1, head2);
        System.out.println("第一个公共结点：" + result.getValue());
    }
}
