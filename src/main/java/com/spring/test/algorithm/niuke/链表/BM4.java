package com.spring.test.algorithm.niuke.链表;

/**
 * @author hlshi3
 * @date 2022/4/10 9:28
 */

/**
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 数据范围： −1000≤节点值≤1000
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 *
 * 如输入{1,3,5},{2,4,6}时，合并后的链表为{1,2,3,4,5,6}，所以对应的输出为{1,2,3,4,5,6}
 */
public class BM4 {

    /**
     * 同Leetcode 21 Merge Two Sorted Lists ，用递归实现：
     *
     * 简单地理一下思路：
     *
     * 从头结点开始考虑，比较两表头结点的值，值较小的list的头结点后面接merge好的链表（进入递归了）；
     * 若两链表有一个为空，返回非空链表，递归结束；
     * 当前层不考虑下一层的细节，当前层较小的结点接上该结点的next与另一结点merge好的表头就ok了；
     * 每层返回选定的较小结点就ok；
     * 重新整理一下：
     *
     * 终止条件：两链表其中一个为空时，返回另一个链表；
     * 当前递归内容：若list1.val <= list2.val 将较小的list1.next与merge后的表头连接，即list1.next = Merge(list1.next,list2); list2.val较大时同理；
     * 每次的返回值：排序好的链表头；
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {

        if(list1 ==null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        // 当前list1的值 < list2的值
        if(list1.val < list2.val){
            list1.next = Merge(list1.next, list2);
            return list1;
        }

        if(list1.val > list2.val){
            list2.next = Merge(list1, list2.next);
            return list2;
        }else{
            list2.next = Merge(list1,list2.next);
            return list2;
        }
    }


    public ListNode Merge1(ListNode list1,ListNode list2) {

        if(list1 ==null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode preHead = new ListNode(-1001);
        ListNode preNode = preHead;

        while(list1!= null && list2 != null){
            if(list1.val < list2.val){
                preNode.next = list1;
                list1 =list1.next;
            }else{
                preNode.next = list2;
                list2 = list2.next;
            }
            preNode = preNode.next;
        }
        preNode.next = list1 == null ? list2 : list1;
        return preHead.next;
    }


}
