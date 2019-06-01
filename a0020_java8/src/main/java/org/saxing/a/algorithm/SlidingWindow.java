package org.saxing.a.algorithm;

import java.util.*;

public class SlidingWindow {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] result = maxSlidingWindow(nums, k);
        System.out.println(result);

    }

    public static int[] maxSlidingWindow(int [] nums, int k){
        if (nums == null || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1){
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }

            deque.offer(i);

            if (i >= k - 1){
                res[index++] = nums[deque.peek()];
            }
        }
        return res;

    }

    public static int[] maxSlidingWindow2(int[] a, int k) {
        if (a == null || k <= 0) return new int[0];
        int[] res = new int[a.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        int index  = 0;
        for (int i = 0; i < a.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) // Ensure deque's size doesn't exceed k
                deque.poll();

            // Remove numbers smaller than a[i] from right(a[i-1]) to left, to make the first number in the deque the largest one in the window
            while (!deque.isEmpty() && a[deque.peekLast()] < a[i])
                deque.pollLast();

            deque.offer(i);// Offer the current index to the deque's tail

            if (i >= k - 1)// Starts recording when i is big enough to make the window has k elements
                res[index++] = a[deque.peek()];
        }
        return res;
    }

//    public static int[] maxSlidingWindow(int[] nums, int k) {
//        if (nums == null || nums.length < k || k < 1){
//            return new int[0];
//        }
//        LinkedList<Integer> deque = new LinkedList<>();
//
//
//        List<Integer> result = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (deque.size() < 1){
//                deque.offer(nums[0]);
//                continue;
//            }
//
//            deque.offerLast(nums[i]);
//            while (deque.size() > k){
//                deque.getFirst();
//            }
//
//
//
//
//        }
//
//        return result.stream().mapToInt(Integer::valueOf).toArray();
//    }

}
