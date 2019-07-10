package org.saxing.a.algorithm2;

/**
 * leet code 69
 */
public class SqrtTest {

    public static void main(String[] args) {

        int res = mySqrt1(8);
        System.out.println(res);

        int res2 = mySqrt2(8);
        System.out.println(res2);


        int res3 = mySqrt22(16);
        System.out.println(res3);


        int res4 = mySqrt23(16);
        System.out.println(res4);

        int res5 = mySqrt24(16);
        System.out.println(res5);

        int res6 = mySqrt25(16);
        System.out.println(res6);

        int res7 = mySqrt26(16);
        System.out.println(res7);

        int res8 = mySqrt27(16);
        System.out.println(res8);

        int res9 = mySqrt28(16);
        System.out.println(res9);

    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt28(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt27(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt26(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt25(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt24(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    // 方案二 牛顿迭代法
    public static int mySqrt23(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }


//==================================================================================================
//==================================================================================================
//==================================================================================================

    // 方案二 牛顿迭代法
    public static int mySqrt22(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt2(int x) {
        if (x == 0 || x == 1) return x;
        int l = 0, r = x, res = 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (mid == x / mid){
                return mid;
            } else if (mid > x / mid){
                r = mid - 1;
            } else {
                l = mid + 1;
                res = l;
            }
        }
        return res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt1(int x) {
        if (x == 0 || x == 1) return x;
        int l = 0, r = x, res = 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (mid == x / mid){
                return mid;
            } else if (mid > x / mid){
                r = mid - 1;
            } else {
                l = mid + 1;
                res = l;
            }
        }
        return res;
    }


}
