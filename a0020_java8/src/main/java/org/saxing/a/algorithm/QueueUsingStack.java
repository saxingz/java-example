package org.saxing.a.algorithm;

import java.util.Stack;

public class QueueUsingStack {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

        queue.push(5);

        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

    }

    static class MyQueue{

        Stack<Integer> stack1 ;
        Stack<Integer> stack2 ;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (!stack2.isEmpty()){
                return stack2.pop();
            }
            if (!stack1.isEmpty()){
                exchange();
                return stack2.pop();
            }else{
                return -1;
            }
        }

        /** Get the front element. */
        public int peek() {
            if (!stack2.isEmpty()){
                return stack2.peek();
            }
            if (!stack1.isEmpty()){
                exchange();
                return stack2.peek();
            }else{
                return -1;
            }
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void exchange(){
            while (!stack1.isEmpty()){
                Integer pop = stack1.pop();
                stack2.push(pop);
            }

        }
    }

}
