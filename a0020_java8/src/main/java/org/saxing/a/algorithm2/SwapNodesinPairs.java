package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;

import java.util.Objects;

/**
 * leetcode 24
 */
public class SwapNodesinPairs {

    public static ListNode getNode(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = swapPairs(getNode());
        System.out.println(listNode);
        ListNode listNode2 = swapPairs2(getNode());
        System.out.println(listNode2);
        ListNode listNode3 = swapPairs3(getNode());
        System.out.println(listNode3);

        ListNode listNode4 = swapPairs4(getNode());
        System.out.println(listNode4);

    }

    public static ListNode swapPairs4(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode newHead = cur.next;
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


    public static ListNode swapPairs3(ListNode head) {
        if (head == null || head.next == null) return head;
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


    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
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
