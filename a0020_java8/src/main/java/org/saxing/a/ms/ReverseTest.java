package org.saxing.a.ms;


import java.util.Stack;


/**
 * 1. 节点和链表结构如下：
 * class MyNode {
 * 	int num; //内容
 * 	MyNode next; //指向下个节点的指针
 * }
 *
 * class MyList {
 * 	MyNode head; //表头
 * }
 * 链表最末尾节点的next指针指向null。
 *
 * 实现函数，从指定位置开始逆序打印。必要时可以使用java库类进行辅助，使用时请尽量保证较好性能。
 * void reversePrint(MyList list, int beginPosition);
 *
 * 其中，beginPosition为开始打印的节点位置(越界时需报错)。head为0，下一个节点为1，依次类推。
 *
 * * 例：链表为：1(head) -> 2 -> 3 -> 4 -> 5 -> null
 * 	* 当beginPosition=3时，打印的结果为：4 -> 3 -> 2 -> 1 -> 5
 * 	* 当beginPosition<0时，报错，因为越界了。
 * 	* 当beginPosition>5时，报错，因为越界了。
 */
class MyNode {
    int num; //内容
    MyNode next; //指向下个节点的指针

    public MyNode() {
    }

    public MyNode(int num) {
        this.num = num;
    }
}

class MyList {
    MyNode head; //表头
}

public class ReverseTest {

    public static void main(String[] args) {

        MyNode myNode = new MyNode(1);
        myNode.next = new MyNode(2);
        myNode.next.next = new MyNode(3);
        myNode.next.next.next = new MyNode(4);
        myNode.next.next.next.next = new MyNode(5);


        MyList myList = new MyList();
        myList.head = myNode;


        new ReverseTest().reversePrint(myList, 3);
    }

    void reversePrint(MyList list, int beginPosition){
        MyNode start = list.head;
        if(start == null){
            return ;
        }
        // 求长度
        int length = 1;
        while(start.next != null){
            start = start.next;
            length++;
        }

        if(beginPosition < 0 || beginPosition >= length){
            throw new RuntimeException("Outof index");
        }

        MyNode temp = list.head;
        start = list.head;
        int tempIndex = 0;

        for(int i = 0; i <= beginPosition; i++){
            temp = temp.next;
            tempIndex++;
        }

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < length; i++){
            if(tempIndex < length ){
                // result[i] = temp.num;
                stack.push(temp.num);
                temp = temp.next;
                tempIndex++;

            }else{
                // result[i] = start.num;
                stack.push(start.num);
                start = start.next;
            }
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }


        // for(int i = 4; i >=0; i--){
        // 	System.out.println(result[i]);
        // }

    }

}
