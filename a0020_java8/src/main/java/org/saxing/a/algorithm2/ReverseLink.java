package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;

/**
 * leet code 206
 */
public class ReverseLink {

    public static ListNode getNode(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        return head;
    }

    public static void main(String[] args) {

        ListNode listNode = reverseList(getNode());
        System.out.println(listNode);

        ListNode listNode2 = reverseList2(getNode());
        System.out.println(listNode2);

        ListNode listNode3 = reverseList3(getNode());
        System.out.println(listNode3);
    }

    public static ListNode reverseList(ListNode head) {
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



    public static ListNode reverseList2(ListNode head) {
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



    public static ListNode reverseList3(ListNode head) {
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
