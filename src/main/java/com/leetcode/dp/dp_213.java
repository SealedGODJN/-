package com.leetcode.dp;

public class dp_213 {
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        if (nums.length > 2) {
            return Math.max(robInternal(nums, dp, 0, nums.length - 1), robInternal(nums, dp, 1, nums.length));
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        } else {
            return nums[0];
        }
    }

    public static int robInternal(int[] nums, int[] dp, int first, int last) {
        // 初始化
        dp[first] = nums[first];
        dp[first + 1] = Math.max(nums[first], nums[first + 1]);

        for (int i = first + 2; i < last; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[last - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(rob(nums));
    }
}
