package org.saxing.a.algorithm2;

import java.util.PriorityQueue;

/**
 * leet code 703
 */
public class KthLargestElementinaStream {

    PriorityQueue<Integer> queue;
    int k;

    public void KthLargest(int k, int[] nums) {
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
