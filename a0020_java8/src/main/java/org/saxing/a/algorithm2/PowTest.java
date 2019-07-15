package org.saxing.a.algorithm2;

/**
 * leet code 50
 */
public class PowTest {

    public static void main(String[] args) {
        double v = myPow1(2, 4);
        System.out.println(v);

        double v2 = myPow2(2, 4);
        System.out.println(v2);

        double v3 = myPow3(2, 4);
        System.out.println(v2);
    }

    public static double myPow3(double x, int n) {
        if (n == 0) return 1;
        double res = myPow3(x, n / 2);
        return (n & 1) == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }

    public static double myPow2(double x, int n) {
        if (n == 0) return 1;
        double res = myPow2(x, n / 2);
        return (n & 1) == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }

    public static double myPow1(double x, int n) {
        if (n == 0) return 1;
        double res = myPow1(x, n / 2);
        return (n & 1) == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }

}
