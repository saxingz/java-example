package org.saxing.a.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * leet code 120
 */
public class TriangleTest {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();

        List<Integer> l1 = new ArrayList<>();
        l1.add(2);

        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);

        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);

        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);

        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);

        int result = new TriangleTest().minimumTotal(triangle);
        System.out.println(result);


    }

    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[] mini = triangle.get(triangle.size() - 1).toArray(new Integer[triangle.size()]);
        for (int i = triangle.size() - 2; i >= 0; --i) {
            for (int j = 0; j < triangle.get(i).size(); ++j) {
//            for (int j = 0; j <= i; j++) {
                mini[j] = triangle.get(i).get(j) + Math.min(mini[j], mini[j + 1]);
            }
        }
        return mini[0];
    }

}
