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

        int res2 = new MajorityElement().majorityElement2(getArray());
        System.out.println(res2);

        int res3 = new MajorityElement().majorityElement3(getArray());
        System.out.println(res3);

        int res4 = new MajorityElement().majorityElement4(getArray());
        System.out.println(res4);

        int res5 = new MajorityElement().majorityElement5(getArray());
        System.out.println(res5);

        int res6 = new MajorityElement().majorityElement6(getArray());
        System.out.println(res6);

        int res7 = new MajorityElement().majorityElement7(getArray());
        System.out.println(res7);
    }

    // exec 7 ----------------------------------------------------------
    public int majorityElement7(int[] nums) {
        return majorityElementRec7(nums, 0, nums.length - 1);
    }
    private int countInRange7(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }
    private int majorityElementRec7(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }
        int mid = (lo + hi) / 2;
        int left = majorityElementRec7(nums, lo, mid);
        int right = majorityElementRec7(nums, mid + 1, hi);
        if (left == right){
            return left;
        }
        int leftCount = countInRange7(nums, left, lo, hi);
        int rightCount = countInRange7(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    // exec 6 ----------------------------------------------------------
    public int majorityElement6(int[] nums) {
        return majorityElementRec6(nums, 0, nums.length - 1);
    }
    private int countInRange6(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }
    private int majorityElementRec6(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }
        int mid = (lo + hi) / 2;
        int left = majorityElementRec6(nums, lo, mid);
        int right = majorityElementRec6(nums, mid + 1, hi);
        if (left == right){
            return left;
        }
        int leftCount = countInRange6(nums, left, lo, hi);
        int rightCount = countInRange6(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    // exec 5 ----------------------------------------------------------
    public int majorityElement5(int[] nums) {
        return majorityElementRec5(nums, 0, nums.length - 1);
    }

    private int countInRange5(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++){
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec5(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }
        int mid = (lo + hi) / 2;
        int left = majorityElementRec5(nums, lo, mid);
        int right = majorityElementRec5(nums, mid + 1, hi);

        if (left == right){
            return left;
        }
        int leftCount = countInRange5(nums, left, lo, hi);
        int rightCount = countInRange5(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    // exec 4 ----------------------------------------------------------
    public int majorityElement4(int[] nums) {
        return majorityElementRec4(nums, 0, nums.length - 1);
    }

    private int countInRange4(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++){
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec4(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }

        int mid = (lo + hi) / 2;
        int left = majorityElementRec4(nums, lo, mid);
        int right = majorityElementRec4(nums, mid + 1, hi);

        if (left == right){
            return left;
        }

        int leftCount = countInRange4(nums, left, lo, hi);
        int rightCount = countInRange4(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }



    // exec 3 ----------------------------------------------------------
    public int majorityElement3(int[] nums) {
        return majorityElementRec3(nums, 0, nums.length - 1);
    }

    private int countInRange3(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++){
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec3(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }

        int mid = (lo + hi) / 2;
        int left = majorityElementRec3(nums, lo, mid);
        int right = majorityElementRec3(nums, mid + 1, hi);

        if (left == right){
            return left;
        }

        int leftCount = countInRange3(nums, left, lo, hi);
        int rightCount = countInRange3(nums, left, lo, hi);

        return leftCount > rightCount ? left : right;

    }


    // exec 2 --------------------------------------------------------
    public int majorityElement2(int[] nums) {
        return majorityElementRec2(nums, 0, nums.length - 1);
    }

    private int countInRange2(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++){
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec2(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }

        int mid = (lo + hi) / 2;
        int left = majorityElementRec2(nums, lo, mid);
        int right = majorityElementRec2(nums, mid + 1, hi);

        if (left == right){
            return left;
        }

        int leftCount = countInRange2(nums, left, lo, hi);
        int rightCount = countInRange2(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
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
