package com.leetcode.dp;

public class dp_377 {
    /**
     * 1.确定dp数组以及下标的含义
     * dp[i]: 凑成目标正整数为i的排列个数为dp[i]
     * <p>
     * 2.确定递推公式
     * <p>
     * 3.dp数组如何初始化
     * <p>
     * 4.确定遍历顺序
     * <p>
     * 5.举例验证
     *
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(combinationSum4(nums, 4));
    }
}
