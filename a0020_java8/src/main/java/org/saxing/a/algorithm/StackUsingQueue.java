package org.saxing.a.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class StackUsingQueue {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());

    }


    static class MyStack{

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            if (!queue1.isEmpty()){
                queue1.offer(x);
            }else{
                queue2.offer(x);
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (!queue1.isEmpty()){
                if (queue1.size() > 1){
                    exchangeExcept1(queue1, queue2);
                }
                return queue1.poll();
            }else if (!queue2.isEmpty()){
                if (queue2.size() > 1){
                    exchangeExcept1(queue2, queue1);
                }
                return queue2.poll();
            }
            return -1;
        }

        /** Get the top element. */
        public int top() {
            if (!queue1.isEmpty()){
                if (queue1.size() > 1){
                    exchangeExcept1(queue1, queue2);
                }
                int result = queue1.peek();
                queue2.offer(queue1.poll());
                return result;
            }else if (!queue2.isEmpty()){
                if (queue2.size() > 1){
                    exchangeExcept1(queue2, queue1);
                }
                int result = queue2.peek();
                queue1.offer(queue2.poll());
                return result;
            }
            return -1;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }

        private void exchangeExcept1(Queue q1, Queue q2){
            while (q1.size() > 1){
                q2.offer(q1.poll());
            }
        }
    }

}
