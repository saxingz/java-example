package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.ListNode;

public class ReverseTest {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
//        listNode4.next = listNode5;

        System.out.println(listNode1);

        ListNode listNode = swapPairs(listNode1);

        System.out.println(listNode);
    }

//    public static ListNode swapPairs(ListNode head){
//        ListNode id = head;
//        while (head != null && head.next != null){
//            ListNode temp1 = head.next;
//            ListNode temp2 = temp1.next;
//            head.next = temp2;
//            temp1.next = head;
//            head = temp2;
//        }
//        return id;
//    }

//    public static ListNode swapPairs(ListNode head){
//        if (head == null || head.next == null){
//            return head;
//        }
//        ListNode n = head.next;
//        head.next = swapPairs(head.next.next);
//        n.next = head;
//        return n;
//    }


    public static ListNode swapPairs(ListNode head){
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











//    public static ListNode swapPairs(ListNode head){
//        if (head == null || head.next == null){
//            return head;
//        }
//        ListNode curr = head;
//        ListNode newHead = head.next;
//        while (curr != null && curr.next != null){
//            ListNode temp = curr;
//            curr = curr.next;
//            temp.next = curr.next;
//            curr.next = temp;
//            curr = temp.next;
//            if (curr != null && curr.next != null){
//                temp.next = curr.next;
//                System.out.println();
//            }
//        }
//        return newHead;
//    }


//    public static ListNode swapPairs(ListNode head) {
//        if ((head == null)||(head.next == null))
//            return head;
//        ListNode n = head.next;
//        head.next = swapPairs(head.next.next);
//        n.next = head;
//        return n;
//    }

//    public static ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null) return head;
//        ListNode cur = head;
//        ListNode newHead = head.next;
//        while (cur != null && cur.next != null) {
//            ListNode tmp = cur;
//            cur = cur.next;
//            tmp.next = cur.next;
//            cur.next = tmp;
//            cur = tmp.next;
//            if (cur != null && cur.next != null) tmp.next = cur.next;
//        }
//        return newHead;
//    }

}
