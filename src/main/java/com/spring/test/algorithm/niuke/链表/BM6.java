package com.spring.test.algorithm.niuke.链表;

/**
 * @author hlshi3
 * @date 2022/4/11 7:37
 */

/**
 * 判断链表中是否有环.
 * 环形链表的环一定在末尾，末尾没有NULL了
 * 环形链表遍历过程中会不断循环，线形链表遍历到NULL结束了，但是环形链表何时能结束呢？我们可以用一种双指针技巧，这也是处理环形链表常用的技巧：
 *
 * - step 1：设置快慢两个指针，初始都指向链表头。
 * - step 2：遍历链表，快指针每次走两步，慢指针每次走一步。
 * - step 3：如果快指针到了链表末尾，说明没有环，因为它每次走两步，所以要验证连续两步是否为NULL。
 * - step 4：如果链表有环，那快慢双指针会在环内循环，因为快指针每次走两步，因此快指针会在环内追到慢指针，二者相遇就代表有环。
 */
public class BM6 {

    //通过定义slow和fast指针，slow每走一步，fast走两步，若是有环，则一定会在环的某个结点处相遇（slow == fast）
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
