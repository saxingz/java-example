package org.saxing.a.algorithm2;

/**
 * leet code 231
 */
public class PowerOfTwo {

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(8));
        System.out.println(new PowerOfTwo().isPowerOfTwo(9));
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

}
