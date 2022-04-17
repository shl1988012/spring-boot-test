package com.spring.test.algorithm.niuke.链表;

/**
 * @author hlshi3
 * @date 2022/4/17 9:21
 */
public class BM15 {

    /**
     *删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates (ListNode head) {
        // write code here
        ListNode temp = head;
        while(temp != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return head;
    }


}
