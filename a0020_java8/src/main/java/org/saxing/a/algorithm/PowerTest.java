package org.saxing.a.algorithm;

public class PowerTest {

    public static void main(String[] args) {
        double result = new PowerTest().power1(2, 5);
        System.out.println(result);

        int result2 = new PowerTest().power2(2, 5);
        System.out.println(result2);
    }

    double power1(int a, int n){
        if (n == 0) return 1;
        double r = power1(a, n / 2);
        return ((n & 1) == 1) ? r * r * a : r * r;
    }

    int power2(int a, int n){
        if (n == 0) return 1;
        int res = 1, tmp = a;
        while (n > 0){
            if ((n & 1) == 1){
                res *= tmp;
            }
            n >>= 1;
            tmp *= tmp;
        }
        return res;
    }

}
