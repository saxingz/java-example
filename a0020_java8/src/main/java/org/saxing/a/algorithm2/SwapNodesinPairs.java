package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;

/**
 * leetcode 24
 */
public class SwapNodesinPairs {

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
        if (head == null || head.next == null){
            return head;
        }
        ListNode curr = head;
        ListNode newHead = head.next;
        while (curr != null && curr.next != null){
            ListNode temp = curr;
            curr = curr.next;
            temp.next = curr.next;
            curr.next = temp;
            curr = temp.next;
            if (curr != null && curr.next != null){
                temp.next = curr.next;
            }
        }
        return newHead;
    }

}
