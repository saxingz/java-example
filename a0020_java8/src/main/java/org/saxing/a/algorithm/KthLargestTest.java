package org.saxing.a.algorithm;

import java.util.PriorityQueue;

public class KthLargestTest {

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4,5,8,2};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(3)); // returns 4
        System.out.println(kthLargest.add(5)); // returns 5
        System.out.println(kthLargest.add(10)); // returns 5
        System.out.println(kthLargest.add(9)); // returns 8
        System.out.println(kthLargest.add(4)); // returns 8


    }

    static class KthLargest {

        PriorityQueue<Integer> queue;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            queue = new PriorityQueue<>(k);
            for (Integer i : nums){
                queue.offer(i);
            }
        }

        public int add(int val) {
            queue.offer(val);
            while (queue.size() > k){
                queue.poll();
            }
            if (queue.isEmpty()){
                return -1;
            }
            return queue.peek();
        }
    }


}
