package com.leetcode.dp;

public class dp_63 {
    /**
     * 从(0,0) 到 (m-1, n-1)
     * 1. 确定dp[i][j]的含义
     * dp[i][j] 从(0,0) 到 (m-1, n-1) 的 路径方法种数
     * <p>
     * 2.递推公式
     * dp[i][j] = dp[i-1][j] + dp[i][j-1] if obstacleGrid[i][j] == 0
     * dp[i][j] = 0 if obstacleGrid[i][j] == 1
     * <p>
     * 3.dp数组的初始化
     * <p>
     * 初始化的部分，很容易忽略了障碍之后应该都是0的情况。
     * <p>
     * dp[0][0] = 0
     * dp[1][0] = 1
     * dp[0][1] = 1
     * <p>
     * 4.确定遍历顺序
     * 保证dp[i-1][j]和dp[i][j-1]都有数值
     * <p>
     * 5.举例
     *
     * @param obstacleGrid m×n网格
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) //如果在起点或终点出现了障碍，直接返回0
            return 0;

        int[][] dp = new int[m][n];

        // 这里需要注意，不是 if (obstacleGrid[0][i] == 0)
        // 而是如果前面有障碍，后面就走不通了
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
//            if (obstacleGrid[0][i] == 0) {
            dp[0][i] = 1;
        }
        for (int j = 0; j < m && obstacleGrid[j][0] == 0; j++) {
//            if (obstacleGrid[j][0] == 0) {
            dp[j][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
