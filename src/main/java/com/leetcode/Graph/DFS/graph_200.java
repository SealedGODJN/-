package com.leetcode.Graph.DFS;

public class graph_200 {
    boolean[][] visited;

    public int numIslands(char[][] grid) {
        int count = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (visited[i][j]) return;
        visited[i][j] = true;
        if (grid[i][j] == '0') return;
        if (i - 1 >= 0) {
            dfs(grid, i - 1, j);
        }
        if (i + 1 <= grid.length - 1) {
            dfs(grid, i + 1, j);
        }
        if (j - 1 >= 0) {
            dfs(grid, i, j - 1);
        }
        if (j + 1 <= grid[0].length - 1) {
            dfs(grid, i, j + 1);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

        graph_200 graph200 = new graph_200();
        System.out.println(graph200.numIslands(grid));
    }
}
