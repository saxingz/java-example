package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;

public class LinkedListCycle {

    public static ListNode getNode(){

        ListNode temp = new ListNode(44);

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = temp;
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = temp;


        return head;
    }

    public static void main(String[] args) {
        boolean res1 = hasCycle(getNode());
        System.out.println(res1);
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if (fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

    }

}
