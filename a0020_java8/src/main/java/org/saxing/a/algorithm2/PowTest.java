package org.saxing.a.algorithm2;

/**
 * leet code 50
 */
public class PowTest {

    public static void main(String[] args) {
        double v = myPow1(2, 4);
        System.out.println(v);
    }

    public static double myPow1(double x, int n) {
        if (n == 0) return 1;
        double res = myPow1(x, n / 2);
        return (n & 1) == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }

}
