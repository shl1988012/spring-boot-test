package com.spring.test.algorithm;

//链表中倒数第k个结点
public class Test15 {

    public static  ListNode findKthToTail(ListNode head, int k){

        ListNode prePoint = head;// 第一个指针
        ListNode postPoint = head;// 第二个指针

        //加上head，所以走k-1步
        for(int i=0 ; i<k-1; i++){
            if(prePoint.getNext() == null){
                return null;
            }
            prePoint = prePoint.getNext();
        }

        while(prePoint.getNext() != null){
            prePoint = prePoint.getNext();
            postPoint= postPoint.getNext();
        }
        return postPoint;
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

        ListNode node = findKthToTail(head, 3);
        System.out.println(node.getValue());
    }
}
