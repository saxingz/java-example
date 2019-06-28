package org.saxing.a.algorithm2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * leet code 239
 */
public class SlidingWindow {

    public static int[] getNums(){
         return new int[]{1,3,-1,-3,5,3,6,7};
    }

    public static void main(String[] args) {
        int k = 3;

        int[] res1 = maxSlidingWindow1(getNums(), k);
        List<Integer> res1List = Arrays.stream(res1).boxed().collect(Collectors.toList());
        System.out.println(res1List);


        int[] res2 = maxSlidingWindow2(getNums(), k);
        List<Integer> res2List = Arrays.stream(res2).boxed().collect(Collectors.toList());
        System.out.println(res2List);



        int[] res3 = maxSlidingWindow2(getNums(), k);
        List<Integer> res3List = Arrays.stream(res3).boxed().collect(Collectors.toList());
        System.out.println(res3List);



    }

    public static int[] maxSlidingWindow3(int[] nums, int k) {
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
            if (i >= k -1){
                res[index++] = nums[deque.peek()];
            }
        }
        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
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

    public static int[] maxSlidingWindow1(int[] nums, int k) {
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

}
