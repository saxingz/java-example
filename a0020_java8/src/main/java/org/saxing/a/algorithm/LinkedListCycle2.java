package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.ListNode;

public class LinkedListCycle2 {

    public ListNode detectCycle(ListNode head){
        if (head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        int hasCycle = 0;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                hasCycle = 1;
                break;
            }
        }

        if (hasCycle == 0){
            return null;
        }else{
            slow = head;
            while (slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }

}
