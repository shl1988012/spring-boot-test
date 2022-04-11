package com.spring.test.algorithm.niuke.链表;

/**
 * 链表中倒数最后k个结点
 * @author hlshi3
 * @date 2022/4/11 7:43
 */

/**
 * step 1：准备一个快指针，从链表头开始，在链表上先走k步。
 * step 2：准备慢指针指向原始链表头，代表当前元素，则慢指针与快指针之间的距离一直都是k。
 * step 3：快慢指针同步移动，当快指针到达链表尾部的时候，慢指针正好到了倒数k个元素的位置。
 */
public class BM8 {

    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode snow = pHead;
        ListNode fast = pHead;
        //fast先走k步
        for(int i =0 ; i<k ; i++){
            if(fast != null){
                fast = fast.next;
            }else{
                return null;
            }
        }
        while(fast != null){
            snow = snow.next;
            fast =fast.next;
        }
        return snow;
    }

}
