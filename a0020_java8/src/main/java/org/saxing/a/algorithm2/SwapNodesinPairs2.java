package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;

/**
 * leetcode 24
 */
public class SwapNodesinPairs2 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = swapPairs(head);
        System.out.println(listNode);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null){
            ListNode temp = cur;
            cur = cur.next;
            temp.next = cur.next;
            cur.next = temp;
            cur = temp.next;
            if (cur != null && cur.next != null){
                temp.next = cur.next;
            }

        }
        return newHead;
    }

}
