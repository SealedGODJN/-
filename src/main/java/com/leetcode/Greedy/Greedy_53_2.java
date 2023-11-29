package com.leetcode.Greedy;

public class Greedy_53_2 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > result) result = count;
            // count是负数，则重新计算最大和
            if (count <= 0) {
                count = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1};

        Greedy_53_2 test = new Greedy_53_2();
        test.maxSubArray(nums);
    }
}
