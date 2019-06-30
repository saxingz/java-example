package org.saxing.a.algorithm2;

import java.util.Arrays;

/**
 * leet code 621
 */
public class TaskScheduler {
    
    static char[] getArray(){
        return new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
    }

    public static void main(String[] args) {
        int result1 = leastInterval(getArray(), 1);
        System.out.println(result1);
    }

    public static int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

}
