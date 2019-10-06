package com.spring.test.algorithm;

//合并两个排序的链表
public class Test17 {

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.getValue() + "、");
            current = current.getNext();
        }
        System.out.println();
    }



    public static ListNode mergeList(ListNode head1, ListNode head2){
        ListNode head = null;
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }

        if(head1 != null && head2 !=null){

            if(head1.getValue()< head2.getValue()){
                head = head1;
                head.setNext(mergeList(head1.getNext(), head2));
            }else{
                head = head2;
                head.setNext(mergeList(head1, head2.getNext()));
            }
        }
        return head;
    }

    public static void main(String[] args) {
        // 构建链表1
        ListNode head1 = new ListNode(1);
        ListNode node1_2 = new ListNode(3);
        ListNode node1_3 = new ListNode(5);
        ListNode node1_4 = new ListNode(7);
        head1.setNext(node1_2);
        node1_2.setNext(node1_3);
        node1_3.setNext(node1_4);
        node1_4.setNext(null);
        // 构建链表2
        ListNode head2 = new ListNode(2);
        ListNode node2_2 = new ListNode(4);
        ListNode node2_3 = new ListNode(6);
        ListNode node2_4 = new ListNode(8);
        head2.setNext(node2_2);
        node2_2.setNext(node2_3);
        node2_3.setNext(node2_4);
        node2_4.setNext(null);

        System.out.println("链表1：");
        printList(head1);
        System.out.println("-------------");

        System.out.println("链表2：");
        printList(head2);
        System.out.println("-------------");

        System.out.println("合并后的链表：");
        ListNode head = mergeList(head1, head2);
        printList(head);
        System.out.println("-------------");
    }





}
