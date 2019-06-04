package org.saxing.a.algorithm;

/**
 * leetcode 338
 */
public class CountingBits {

    public static void main(String[] args) {
        int[] ints = new CountingBits().countBits(2);
        System.out.println(ints);
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i-1)] + 1;
        }
        return result;
    }

}
