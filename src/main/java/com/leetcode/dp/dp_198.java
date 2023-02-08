package com.leetcode.dp;

public class dp_198 {
    /**
     * 1.确定dp数组以及下标的含义
     * dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]
     * <p>
     * 2.确定递推公式
     * 偷下标为i的房子：dp[i] = dp[i-2] + nums[i]
     * <p>
     * 不偷下标为i的房子：dp[i] = dp[i-1]
     * <p>
     * dp[i] = max(两种情况取最大值)
     * <p>
     * 3.dp数组如何初始化
     * 从递推公式看出，dp[0]和dp[1]都需要初始化
     * dp[0] = nums[0]
     * dp[1] = max(nums[0], nums[1])
     * <p>
     * 4.确定遍历顺序
     * dp[i]根据dp[i-1]和dp[i-2]推导
     * <p>
     * 5.举例验证
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        if (nums.length > 1) {
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[nums.length - 1];
        } else {
            return nums[0];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }
}
