package com.leetcode.dp;

public class dp_62 {
    /**
     * 从(0,0) 到 (m-1, n-1)
     * 1. 确定dp[i][j]的含义
     * dp[i][j] 从(0,0) 到 (m-1, n-1) 的 路径方法种数
     * <p>
     * 2.递推公式
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * <p>
     * 3.dp数组的初始化
     * dp[0][0] = 0
     * dp[1][0] = 1
     * dp[0][1] = 1
     * <p>
     * 4.确定遍历顺序
     * 保证dp[i-1][j]和dp[i][j-1]都有数值
     * <p>
     * 5.举例
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (m == 0 && n == 0) return 0;
        int[][] dp = new int[m][n];

//        // 初始化错误
//        dp[0][1] = 1;
//        dp[1][0] = 1;

        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int j = 0; j < m; j++) dp[j][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }
}
