package org.saxing.a.algorithm;

public class PerfectSquare {



    public boolean isPerfectSquare(int num) {
        long start = 1;
        long end = num;
        while (start < end){
            long mid = start + (end - start) / 2;
            if (mid * mid == num){
                return true;
            } else if(mid * mid < num){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return start * start == num;
    }

}
