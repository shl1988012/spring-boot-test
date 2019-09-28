package com.spring.test.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//链表节点
@Data
public class ListNode {

    private int value;
    private ListNode next;

    public ListNode(int value){
        this.value = value;
    }

//    public ListNode(int value, ListNode next) {
//        this.value = value;
//        this.next = next;
//    }
}
