package org.saxing.a.algorithm2;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 120
 */
public class Triangle {

    public static void main(String[] args) {
        int[][] array = {
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}
        };
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < array[i].length; j++) {
                temp.add(array[i][j]);
            }
            list.add(temp);
        }

        System.out.println(new Triangle().minimumTotal(list));
    }

    public int minimumTotal(List<List<Integer>> triangle){
        Integer[] mini = triangle.get(triangle.size() - 1).toArray(new Integer[triangle.size()]);
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                mini[j] = triangle.get(i).get(j) + Math.min(mini[j], mini[j + 1]);
            }
        }
        return mini[0];
    }

}
