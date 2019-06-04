package org.saxing.a.algorithm;

/**
 * leetcode 231
 */
public class PowerofTwo {

    public boolean isPowerOfTwo(int n) {
        if (n == 0) return true;
        return (n & (n - 1)) == 0;
    }

}
