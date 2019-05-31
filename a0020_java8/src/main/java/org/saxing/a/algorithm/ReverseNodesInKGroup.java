package org.saxing.a.algorithm;

public class ReverseNodesInKGroup {

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

//        ListNode listNode = reverseKGroup2(listNode1, 2);
//        System.out.println(listNode);

        ListNode nodes = getFirstN(listNode1, 2);
        System.out.println(nodes);
    }

    public static ListNode getFirstN(ListNode node, int k){
        ListNode fake = new ListNode(0);
        ListNode temp = node;
        ListNode cur = fake.next;
        for (int i = 0; i < k; i++) {
            cur = temp;
            temp = temp.next;
        }
        return fake.next;
    }



    public static ListNode reverseKGroup2(ListNode head, int k) {
        if (head==null||head.next==null||k<2) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode tail = dummy, prev = dummy,temp;
        int count;
        while(true){
            count =k;
            while(count>0&&tail!=null){
                count--;
                tail=tail.next;
            }
            if (tail==null) break;//Has reached the end


            head=prev.next;//for next cycle
            // prev-->temp-->...--->....--->tail-->....
            // Delete @temp and insert to the next position of @tail
            // prev-->...-->...-->tail-->head-->...
            // Assign @temp to the next node of @prev
            // prev-->temp-->...-->tail-->...-->...
            // Keep doing until @tail is the next node of @prev
            while(prev.next!=tail){
                temp=prev.next;//Assign
                prev.next=temp.next;//Delete

                temp.next=tail.next;
                tail.next=temp;//Insert

            }

            tail=head;
            prev=head;

        }
        return dummy.next;

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy=new ListNode(0);
        dummy.next=head;

        ListNode fast=dummy;
        ListNode slow=dummy;
        int count = k;
        while (fast!=null){
            count=k;
            while (count>0&&fast!=null){
                fast=fast.next;
                count--;
            }

            if (fast==null) break;

            ListNode temp = slow.next;//Important: slow.next will be the last node after reverse.

            while (count<k-1){//Important, it is K-1 instead of K
                ListNode move = slow.next;
                slow.next=slow.next.next;//Delete
                move.next=fast.next;//Insert
                fast.next=move;
                count++;
            }
            slow=temp;
            fast=temp;
        }

        return dummy.next;
    }




//    public static ListNode reverseKGroup(ListNode head, int k) {
//        if (head == null || head.next == null){
//            return head;
//        }
//        ListNode newHead = null;
//
//        ListNode cur = head;
//        boolean started = false;
//        while (enough(cur, k)){
//            ListNode start = cur;
//            for (int i = 0; i < k; i++) {
//
//            }
//            if (cur != null && cur.next != null){
//                cur.next = null;
//            }
//
//            start = reverse(start);
//            if (!started){
//                newHead = start;
//            }else{
//                newHead.next = start;
//            }
//            started = true;
//        }
//        return newHead;
//    }
//
//    public static boolean enough(ListNode head, int k){
//        ListNode temp = head;
//        for (int i = 0; i < k; i++) {
//            if (temp == null){
//                return false;
//            }
//            temp = temp.next;
//        }
//        return true;
//    }
//
//    public static ListNode reverse(ListNode head){
//        ListNode cur = head;
//        ListNode prev = null;
//        while (cur != null){
//            ListNode temp = head.next;
//            cur.next = prev;
//            prev = cur;
//            cur = temp;
//        }
//        return prev;
//    }

}
