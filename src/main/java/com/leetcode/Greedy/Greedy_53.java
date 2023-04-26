package com.leetcode.Greedy;

public class Greedy_53 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];

            if (count >= result) {
                result = count;
            }

            if (count <= 0) {
                count = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -1};
        Greedy_53 greedy53 = new Greedy_53();
        System.out.println(greedy53.maxSubArray(nums));
    }
}
