package org.saxing.a.algorithm;

public class PowTest {

    public static void main(String[] args) {
//        System.out.println(myPow(2, 5));
        System.out.println(myPow(2.00000, -2147483648));
    }

    public static double myPow(double x, int n) {
//        if (n == 0){
//            return 1;
//        }
//        if (n < 0){
//            n = -n;
//            x = 1/x;
//        }
//        if (n % 2 == 1){
//            return x * myPow(x * x, n / 2);
//        }
//        return myPow(x * x , n / 2);

        if(n == 0) return 1.;
        double res = myPow(x, n / 2);
        return n % 2 == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }

}
