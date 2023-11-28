package com.leetcode.Graph.DFS;

public class graph_695 {
    public int maxAreaOfIsland(int[][] grid) {
//        int count = 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
//                    count++;
                    int S = dfs(grid, i, j);
                    result = Math.max(result, S);
                }
            }
        }
//        return count;
        return result;
    }

    private boolean inArea(int[][] grid, int i, int j) {
        return i >= 0 && i <= grid.length - 1 && j >= 0 && j <= grid[0].length - 1;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 判断搜索的位置是否合法
        if (!inArea(grid, i, j)) return 0;

        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        // int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        //可到这一步说明它是1，需要+1
        return 1 + dfs(grid, i, j - 1) + dfs(grid, i - 1, j)
                + dfs(grid, i, j + 1) + dfs(grid, i + 1, j);
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
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        graph_695 graph200 = new graph_695();
        System.out.println(graph200.maxAreaOfIsland(grid));
    }
}
