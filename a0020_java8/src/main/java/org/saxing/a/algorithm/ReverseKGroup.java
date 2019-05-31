package org.saxing.a.algorithm;

public class ReverseKGroup {

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

        ListNode listNode = reverseKGroup2(listNode1, 3);
        System.out.println(listNode);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     *
     *----------------------------
     *prev
     *tail   head
     *dummy   1    2    3   4    5
     *----------------------------
     *prev   head      tail
     *dummy   1    2    3   4    5
     *       cur
     *----------------------------
     * 每次让prev.next的元素插入到当前tail之后,这样tail不断前移,被挪动的元素头插入tail之后的链表
     *prev        tail head
     *dummy   2    3    1   4    5
     *       cur
     *----------------------------
     *prev   tail      head
     *dummy   3    2    1   4    5
     *       cur
     *----------------------------
     *                 prev
     *                 tail
     *                 head
     *dummy   3    2    1   4    5
     *----------------------------
     *                 prev  head     tail
     *dummy   3    2    1     4    5  null
     *----------------------------
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode tail = dummy;
        ListNode cur;
        int n;
        while (true) {
            n = k;
            while (n > 0 && tail != null) {
                n--;
                tail = tail.next;
            }
            if (tail == null)
                break;
            head = prev.next;
            while (prev.next != tail) {
                cur = prev.next;// 保存待处理的节点
                prev.next = cur.next;// 断开prev与待处理节点的连接
                cur.next = tail.next;//2步完成头插法 a. 将待处理节点尾部接上tail之后的节点
                tail.next = cur;//b.tail接上待处理的节点
            }
            tail = head;
            prev = head;
        }
        return dummy.next;
    }




    public static ListNode reverseKGroup3(ListNode head, int k){
        if (head == null || head.next == null || k < 2){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode tail = dummy;
        ListNode cur;
        int n;
        while (true){
            n = k;
            while (n > 0 && tail != null){
                n--;
                tail = tail.next;
            }
            if (tail == null){
                break;
            }
            head = prev.next;
            while (prev.next != tail){
                cur = prev.next;
                prev.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
            }
            tail = head;
            prev = head;
        }
        return dummy.next;
    }
}




























