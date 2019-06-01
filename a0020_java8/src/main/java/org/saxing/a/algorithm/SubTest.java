package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.ListNode;

public class SubTest {

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

        ListNode list = sub(listNode1, 2);
        System.out.println(list);
        System.out.println(listNode1);

    }

    public static ListNode sub(ListNode head, int k){
        if (head == null || head.next == null){
            return head;
        }
        ListNode temp = new ListNode(0);
        ListNode headCur = head;
        ListNode tempCur = temp;

        for (int i = 0; i < k; i++) {
            tempCur.next = new ListNode(headCur.val);
            tempCur = tempCur.next;
            headCur = headCur.next;
        }
        tempCur.next = null;
        return temp.next;
    }

}
