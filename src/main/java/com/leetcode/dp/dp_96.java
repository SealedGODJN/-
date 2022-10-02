package com.leetcode.dp;

public class dp_96 {

    /**
     * 1）dp[i]含义
     * 代表 1,...,i 为节点组成的二叉搜索树的数量
     * <p>
     * 2）dp的递推公式
     * dp[i] = dp[j-1] * dp[i-j]
     * <p>
     * 3）dp的初始化
     * dp[1] = 1
     * dp[2] = 2
     * <p>
     * 4）dp的遍历顺序
     * <p>
     * 5）
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(5));
    }
}
