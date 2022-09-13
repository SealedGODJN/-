package com.leetcode.dp;

public class dp_746 {
    /**
     * 1. 确定dp数组和其下标的含义
     * dp[i]的定义：到达第i个台阶所花费的最少体力为dp[i]
     * <p>
     * 2.确定dp数组的递推公式
     * 两个途径得到dp[i]，一个是dp[i-1] 一个是dp[i-2]
     * dp[i] = min (dp[i-1], dp[i-2]) + cost[i];
     * <p>
     * 3.初始化dp数组
     * dp[0] = cost[0];
     * dp[1] = cost[1];
     * <p>
     * 4.确定遍历顺序
     * i从小到大
     * <p>
     * 5.举例验证
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        if (cost.length == 1) {
            return cost[0];
        } else if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        int dp0;
        int dp1;
        int result = 0;
        dp0 = cost[0];
        dp1 = cost[1];

        for (int i = 2; i < cost.length; i++) {
            result = Math.min(dp0, dp1) + cost[i];
            dp0 = dp1;
            dp1 = result;
        }
        // 最后走一步，或者走两步台阶
        return Math.min(result, dp0);
    }

    public static void main(String[] args) {
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }
}
