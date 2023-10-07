package com.leetcode.Graph.DFS;

public class graph_1020 {

    public int numEnclaves(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            dfs(grid, i, 0);
        }
        for (int i = 0; i < grid[0].length; i++) {
            dfs(grid, 0, i);
        }
        for (int i = 0; i < grid.length; i++) {
            dfs(grid, i, grid[0].length - 1);
        }
        for (int i = 0; i < grid[0].length; i++) {
            dfs(grid, grid.length - 1, i);
        }

        for (int[] ints : grid) {
            for (int anInt : ints) {
//                if (anInt == 1) {
//                    count++;
//                }
                count += anInt;
            }
        }
        return count;
    }

    private boolean inArea(int[][] grid, int i, int j) {
        return i >= 0 && i <= grid.length - 1 && j >= 0 && j <= grid[0].length - 1;
    }

    private void dfs(int[][] grid, int i, int j) {
        // 判断搜索的位置是否合法
        if (!inArea(grid, i, j)) return;

        if (grid[i][j] != 1) return;
        // '2'代表已经遍历过的岛屿
        grid[i][j] = 0;

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
