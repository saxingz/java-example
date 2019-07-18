package org.saxing.a.algorithm2;

/**
 * leetcode 191
 */
public class Numberof1Bits {

    public static void main(String[] args) {
        System.out.println(new Numberof1Bits().hammingWeight(9));
    }

    public int hammingWeight(int n) {
        if (n == 0) return 0;
        int count = 0;
        while (n != 0){
            count++;
            n =  n & (n - 1);
        }
        return count;
    }

}
