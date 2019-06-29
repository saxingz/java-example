package org.saxing.a.algorithm2;

/**
 * leet code 169
 */
public class MajorityElement {

    static int[] getArray(){
        return new int[]{2,2,1,1,1,2,2};
    }

    public static void main(String[] args) {
        int res = new MajorityElement().majorityElement1(getArray());
        System.out.println(res);

    }

    //-----------------------------------------------------------------------------
    public int majorityElement1(int[] nums) {
        return majorityElementRec1(nums, 0, nums.length-1);
    }

    private int countInRange1(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec1(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec1(nums, lo, mid);
        int right = majorityElementRec1(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange1(nums, left, lo, hi);
        int rightCount = countInRange1(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

}
