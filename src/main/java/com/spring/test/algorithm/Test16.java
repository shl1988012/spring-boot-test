package com.spring.test.algorithm;

//16：反转链表
public class Test16 {


    public static ListNode reverseList(ListNode head){
        ListNode reverseNode =null;
        ListNode pNode = head;
        ListNode pPrev = null;
        while(pNode != null){



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
        node_four.setNext(null);

        // 打印
        ListNode reservedHead = reverseList(head);
        ListNode tmp = reservedHead;
        while (tmp != null) {
            System.out.print(tmp.getValue() + "、");
            tmp = tmp.getNext();
        }

    }


}
