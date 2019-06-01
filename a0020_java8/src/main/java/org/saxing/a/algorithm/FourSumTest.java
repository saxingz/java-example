package org.saxing.a.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumTest {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                int t = target - nums[i] - nums[j];
                int m = j + 1, n = nums.length - 1;
                while (m < n){
                    if (nums[m] + nums[n] == t){
                        Integer[] temp = {nums[i], nums[j], nums[m], nums[n]};
                        res.add(Arrays.asList(temp));
                        m++;
                        n--;
                        while (m < nums.length && nums[m] == nums[m - 1]) m++;
                        while (n >= 0 && nums[n] == nums[n+1]) n--;
                    }else if (nums[m] + nums[n] < t){
                        m++;
                    }else{
                        n--;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(i != 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1; j < nums.length; j++){
                if(j != i+1 && nums[j] == nums[j-1]) continue;
                int t = target - nums[i] - nums[j];
                int m = j+1, n = nums.length-1;
                while(m < n){
                    if(nums[m]+nums[n] == t){
                        Integer[] temp = {nums[i], nums[j], nums[m], nums[n]};
                        res.add(Arrays.asList(temp));
                        m++;
                        n--;
                        while(m < nums.length && nums[m] == nums[m-1]) m++;
                        while(n >= 0 && nums[n] == nums[n+1]) n--;
                    } else if(nums[m]+nums[n] < t){
                        m++;
                    } else n--;
                }
            }
        }
        return res;
    }

}
