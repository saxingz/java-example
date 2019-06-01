package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.ListNode;

public class ReverseTest2 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        System.out.println(listNode1);

        ListNode listNode = reverseList(listNode1);

        System.out.println(listNode);
    }

    public static ListNode reverseList(ListNode head){
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
















//    public static ListNode reverseList(ListNode head){
//        ListNode cur = head;
//        ListNode prev = null;
//        while(cur != null){
//            ListNode nextTemp = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = nextTemp;
//        }
//        return prev;
//    }


}
