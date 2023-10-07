package com.leetcode.Graph.DFS;

public class graph_200 {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private boolean inArea(char[][] grid, int i, int j) {
        return i >= 0 && i <= grid.length - 1 && j >= 0 && j <= grid[0].length - 1;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 判断搜索的位置是否合法
        if (!inArea(grid, i, j)) return;

        if (grid[i][j] != '1') return;
        // '2'代表已经遍历过的岛屿
        grid[i][j] = '2';

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

        graph_200 graph200 = new graph_200();
        System.out.println(graph200.numIslands(grid));
    }
}
