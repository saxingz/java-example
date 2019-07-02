package org.saxing.a.algorithm2;

/**
 * leet code 69
 */
public class SqrtTest {

    public static void main(String[] args) {
        int res = mySqrt(8);
        System.out.println(res);
    }


//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt(int x) {
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
