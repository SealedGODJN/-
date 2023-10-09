package com.leetcode.Graph.DFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class graph_417 {

    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    boolean[][][] visited;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        visited = new boolean[m][n][2];

        for (int i = 0; i < m; i++) {
            // 太平洋->大西洋
            dfs(heights, i, 0, 0);
            // 大西洋->太平洋
            dfs(heights, i, n - 1, 1);
        }
        for (int i = 0; i < n; i++) {
            // 太平洋->大西洋
            dfs(heights, 0, i, 0);
            // 大西洋->太平洋
            dfs(heights, m - 1, i, 1);
        }

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j][0] && visited[i][j][1]) result.add(Arrays.asList(i, j));
            }
        }
        return result;
    }

//    private boolean inArea(int[][] grid, int i, int j) {
//        return i >= 0 && i <= grid.length - 1 && j >= 0 && j <= grid[0].length - 1;
//    }

    private void dfs(int[][] heights, int x, int y, int signal) {
        // 访问过 则 返回
        if (visited[x][y][signal]) return;
        visited[x][y][signal] = true;

        for (int k = 0; k < 4; k++) {
            int nextx = x + direction[k][0];
            int nexty = y + direction[k][1];
            if (nextx < 0 || nextx >= heights.length || nexty < 0 || nexty >= heights[0].length) continue;
            if (heights[x][y] > heights[nextx][nexty]) continue;
            dfs(heights, nextx, nexty, signal);
        }
    }
}
