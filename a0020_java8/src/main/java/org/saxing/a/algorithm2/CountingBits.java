package org.saxing.a.algorithm2;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * leetcode 338
 */
public class CountingBits {

    public static void main(String[] args) {
        System.out.println(Arrays.stream(new CountingBits().countBits(2)).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(new CountingBits().countBits(5)).boxed().collect(Collectors.toList()));
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

}
