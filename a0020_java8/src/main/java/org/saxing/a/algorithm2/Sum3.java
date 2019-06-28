package org.saxing.a.algorithm2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leet code 15
 */
public class Sum3 {

    static int[] getArray(){
        return new int[]{-1, 0, 1, 2, -1, -4};
    }

    public static void main(String[] args) {

        List<List<Integer>> lists = threeSum1(getArray());
        System.out.println(lists);

        List<List<Integer>> lists2 = threeSum2(getArray());
        System.out.println(lists2);


        List<List<Integer>> lists3 = threeSum2(getArray());
        System.out.println(lists3);

    }

    public static List<List<Integer>> threeSum3(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]){
                continue;
            }
            int j = i + 1, k = num.length - 1;
            while (j < k){
                int candidate = num[i] + num[j] + num[k];
                if (candidate <= 0){
                    if (candidate == 0){
                        result.add(Arrays.asList(num[i], num[j], num[k]));
                    }
                    ++j;
                    while (j < k && num[j] == num[j - 1]){
                        ++j;
                    }
                }else{
                    --k;
                    while (j < k && num[k] == num[k-1]){
                        --k;
                    }
                }
            }

        }
        return result;

    }










    public static List<List<Integer>> threeSum2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]){
                continue;
            }
            int j = i + 1, k = num.length - 1;

            while (j < k){
                int candidate = num[i] + num[j] + num[k];
                if (candidate <= 0){
                    if (candidate == 0){
                        result.add(Arrays.asList(num[i], num[j], num[k]));
                    }
                    ++j;
                    while (j < k && num[j] == num[j - 1]){
                        ++j;
                    }
                }else{
                    --k;
                    while (j < k && num[k] == num[k - 1]){
                        --k;
                    }
                }
            }
        }

        return result;

    }

    public static List<List<Integer>> threeSum1(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]){
                continue;
            }

            int j = i + 1, k = num.length - 1;

            while (j < k){
                int candidate = num[i] + num[j] + num[k];
                if (candidate <= 0){
                    if (candidate == 0){
                        result.add(Arrays.asList(num[i], num[j], num[k]));
                    }
                    ++j;
                    while (j < k && num[j] == num[j - 1]){
                        ++j;
                    }
                } else {
                    --k;
                    while (j < k && num[k] == num[k - 1]){
                        --k;
                    }
                }
            }
        }

        return result;
    }

}
