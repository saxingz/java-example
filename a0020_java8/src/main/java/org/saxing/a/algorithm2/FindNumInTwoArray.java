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

    public static void main(String[] args) {

        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int target = 7;
        long start=System.nanoTime();
        boolean res1 = find1(target, array);
        long end=System.nanoTime();
        System.out.println(res1);
        System.out.println(end - start);

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
