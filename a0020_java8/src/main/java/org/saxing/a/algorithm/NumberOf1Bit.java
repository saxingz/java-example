package org.saxing.a.algorithm;

/**
 * leet code 191
 */
public class NumberOf1Bit {

    public static void main(String[] args) {

    }

    public int hammingWeight(int n) {
        if (n == 0) return 0;
        int count = 0;
        while (n != 0){
            count++;
            n = n & (n - 1);
        }
        return count;
    }

}
