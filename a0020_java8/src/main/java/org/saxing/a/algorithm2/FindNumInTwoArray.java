package org.saxing.a.algorithm2;

/**
 * 在一个二维数组中(n * n)，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *  如
 *
 *  [1,2,8,9],
 *  [2,4,9,12],
 *  [4,7,10,13],
 *  [6,8,11,15]
 *
 */
public class FindNumInTwoArray {

    public static int[][] getArray(){
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        return array;
    }

    public static void main(String[] args) {

        int target = 13;
        long start=System.nanoTime();
        boolean res1 = find1(target, getArray());
        long end=System.nanoTime();
        System.out.println(res1);
        System.out.println(end - start);

        start=System.nanoTime();
        boolean res2 = find2(target, getArray());
        end=System.nanoTime();
        System.out.println(res2);
        System.out.println(end - start);

        start=System.nanoTime();
        boolean res3 = find3(target, getArray());
        end=System.nanoTime();
        System.out.println(res3);
        System.out.println(end - start);
    }

    /**
     * 方案三
     *
     * *把每一行看成有序递增的数组，
     * *利用二分查找，
     * *通过遍历每一行得到答案，
     * *时间复杂度是mlogn，每行的时间复杂度是O（logn）。
     */
    private static boolean find3(int target, int[][] array){
        for (int i = 0; i < array.length; i++) {
            int low = 0;
            int high = array[i].length - 1;
            while (low <= high){
                int mid = (low + high) / 2;
                if (target < array[i][mid]){
                    high = mid - 1;
                }else if (target > array[i][mid]){
                    low = mid + 1;
                }else{
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 方案二
     * 思路
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
     * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移
     * 要查找数字比左下角数字小时，上移
     */
    private static boolean find2(int target, int[][] array){
        int len = array.length - 1;
        int i = 0;
        while (len >= 0 && array[0].length > i){
            if (array[len][i] > target){
                len --;
            }else if (array[len][i] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 1 暴力
     */
    private static boolean find1(int target, int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target){
                    return true;
                }
            }
        }
        return false;
    }

}
