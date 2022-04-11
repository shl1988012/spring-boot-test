package com.spring.test.algorithm.niuke.链表;

/**
 *  两个链表的第一个公共结点
 * @author hlshi3
 * @date 2022/4/11 7:53
 */
public class BM10 {

    /**
     * 由于两条链表在相交节点后面的部分完全相同，因此我们可以先对两条链表进行遍历，分别得到两条链表的长度，并计算差值 d。
     * 让长度较长的链表先走 d 步，然后两条链表同时走，第一个相同的节点即是节点。
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        //遍历获取两个链表的长度
        int len1 =0;
        int len2 =0;
        ListNode temp1 =pHead1;
        ListNode temp2 =pHead2;
        while(temp1 != null){
            len1 ++;
            temp1 = temp1.next;
        }
        while(temp2 != null){
            len2 ++;
            temp2 = temp2.next;
        }
        int d= len1- len2;
        if(d >0){
            //链表1比链表2长，链表1先走d步
            for(int i =0; i<d; i++){
                pHead1 =pHead1.next;
            }
        }else{
            for(; d<0; d++){
                pHead2 =pHead2.next;
            }
        }
        while(pHead1 != pHead2){
            pHead1= pHead1.next;
            pHead2 = pHead2.next;
        }
        return pHead1;
    }

}
