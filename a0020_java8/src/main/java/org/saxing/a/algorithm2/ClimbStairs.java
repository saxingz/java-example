package org.saxing.a.algorithm2;

/**
 * leetcode 70
 *
 * @author saxing 2019/8/31 20:38
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(4));
    }

    public int climbStairs(int n){
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        int[] mem = new int[n + 1];
        mem[1] = 1;
        mem[2] = 2;
        for (int i = 3; i <= n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2];
        }
        return mem[n];
    }

}
