package com.leetcode.dp;

public class dp_494 {
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] == target || nums[0] == -target) {
                return 1;
            } else return 0;
        }
        int bagWeight = 0;
        for (int i = 0; i < nums.length; i++) {
            bagWeight += nums[i];
        }
        int sum = bagWeight;
        bagWeight = (bagWeight + target) / 2;
        if ((target + sum) % 2 == 1) return 0; // 此时没有方案
        if (Math.abs(target) > sum) return 0; // 此时没有方案
        if (bagWeight < 0) return 0;
        int[] dp = new int[bagWeight + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagWeight; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[bagWeight];
    }

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 5};
        int[] nums = {1, 1, 1, 1, 1};

        System.out.println(findTargetSumWays(nums, 3));
    }
}
