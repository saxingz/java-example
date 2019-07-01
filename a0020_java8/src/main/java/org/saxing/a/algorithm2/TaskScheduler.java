package org.saxing.a.algorithm2;

import java.util.Arrays;

/**
 * leet code 621
 *
 * 直译本题就是任务规划，题目的核心意思是，安排一堆任务，这些任务由一个task数组给出，相同任务之间，最少有n的运行时间间隔，在n的运行时间间隔内，可以执行执行别的任务或者处于就绪状态。最终的目标是安排所有的task，使总的运行时间最小。
 * 对于两个相同的task，我们假设都为A，两者之间的最小间隔是n，这就是题目想表达的含义，我们首先考虑出现频率最高的task，我们仍假设为A，出现的频率为x，我们知道，要满足A的时间需求，我们至少应该有 x-1个n的间隔。对于频率小于x的任务，假设为B，我们按序插入任务B，可以发现，这样的插入也是满足要求的。满足A的最小安排，最终所有的任务安排都是满足要求的（先不考虑任务多得插不进去的情况）。
 * 因为出现频率最高的元素可能不止一个，我们假设为k个，那么这种情况下最终的时间需求为：
 * (x-1)*n+k
 *
 * 若出现完全插满的情况，上式仍然成立，但显然，多余的task没有计算。
 * 这时候我们观察形成的序列，如果完全插满，这时候的时间需求显然就是整个数组的大小了。
 *
 * 还有我们可以发现，如果刚好插满的情况下，也就是不用待命的时候，所需时间为数组的长度。
 * 而此时如果用上述公式计算的话，可能会出现时间小于数组的时候，所以，应该返回他两个之中比较大的那个。
 *
 * 根据上述的分析，写出的代码如下：
 */
public class TaskScheduler {
    
    static char[] getArray(){
        return new char[]{'A', 'A', 'B', 'B', 'B'};
    }

    public static void main(String[] args) {

        int result1 = leastInterval1(getArray(), 2);
        System.out.println(result1);

        int result2 = leastInterval2(getArray(), 2);
        System.out.println(result2);

        int result3 = leastInterval3(getArray(), 2);
        System.out.println(result3);


        int result4 = leastInterval4(getArray(), 2);
        System.out.println(result4);


    }

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

    public static int leastInterval4(char[] tasks, int n) {
        int[] c = new int[26];
        for (char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25]) i--;
        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }


// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

    public static int leastInterval3(char[] tasks, int n) {
        int[] c = new int[26];
        for (char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25]) i--;
        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }



// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

    public static int leastInterval2(char[] tasks, int n) {
        int[] c = new int[26];
        for (char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

    public static int leastInterval1(char[] tasks, int n) {

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