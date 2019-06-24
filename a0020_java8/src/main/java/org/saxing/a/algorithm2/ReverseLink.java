package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;

/**
 * leet code 206
 */
public class ReverseLink {

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

}
