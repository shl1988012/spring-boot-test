package com.spring.test.algorithm.niuke.链表;

/**
 * @author hlshi3
 * @date 2022/4/10 8:35
 */

import java.util.Stack;

/**
 * 描述
 * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 *
 * 数据范围： 0≤n≤1000
 * 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
 *
 * 如当输入链表{1,2,3}时，
 * 经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
 * 以上转换过程如下图所示：
 * 输入：{1,2,3} --返回值：{3,2,1}
 */
public class BM1 {


    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while(head !=null){
            stack.push(head);
            head = head.next;
        }
        if(stack.isEmpty()){
            return null;
        }

        ListNode node = stack.pop();
        ListNode newHeadNode = node;
        while(!stack.isEmpty()){
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        node.next = null;
        return newHeadNode;
    }
}
