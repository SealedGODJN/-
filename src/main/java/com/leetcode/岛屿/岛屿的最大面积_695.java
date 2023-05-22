package com.leetcode.岛屿;

public class 岛屿的最大面积_695 {

    int result = 0;

    boolean[][] isVisited;

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        isVisited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!isVisited[i][j] && grid[i][j] == 1) {
                    dfs(grid, i, j);
                    max = Math.max(max, result);
                    result = 0;
                }
            }
        }
        return max;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == 0) {
            return;
        }
        if (!isVisited[i][j] && grid[i][j] == 1) {
            isVisited[i][j] = true;
            result++;
            // i - 1
            if (i > 0) dfs(grid, i - 1, j);
            // i + 1
            if (i < grid.length - 1) dfs(grid, i + 1, j);
            // j - 1
            if (j > 0) dfs(grid, i, j - 1);
            // j + 1
            if (j < grid[i].length - 1) dfs(grid, i, j + 1);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        岛屿的最大面积_695 test = new 岛屿的最大面积_695();
        System.out.println(test.maxAreaOfIsland(grid));
    }
}
