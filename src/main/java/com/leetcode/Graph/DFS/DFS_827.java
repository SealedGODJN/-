package com.leetcode.Graph.DFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFS_827 {
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 先dfs一遍，找出所有岛屿，并标记到对应格子
     */
    boolean[][] visited;

    /**
     * 记录每一个岛屿的面积
     */
    int count;

    /**
     * @param grid
     * @param x
     * @param y
     * @param mark 标记格子
     */
    public void dfs(int[][] grid, int x, int y, int mark) {
        if (visited[x][y] || grid[x][y] == 0) return;
        visited[x][y] = true;
        count++;

        // 标记当前格子的key（便于在map中索引）
        grid[x][y] = mark;

        for (int i = 0; i < 4; i++) {
            int nextx = x + direction[i][0];
            int nexty = y + direction[i][1];
            if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue;
            dfs(grid, nextx, nexty, mark);
        }
    }

    public int largestIsland(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        Map<Integer, Integer> markSize = new HashMap<>();
        // mark 从2开始 （避开0和1）
        int mark = 2;
        boolean isAllGrid = true; // 标记是否整个地图都是陆地
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 初始化面积记录值
                count = 0;
                dfs(grid, i, j, mark);
                // map
                markSize.put(mark, count);
                mark++;
                // 非全陆地
                if (grid[i][j] == 0) isAllGrid = false;
            }
        }

        if (isAllGrid) return grid.length * grid[0].length; // 如果都是陆地，返回全面积

        int result = 0;
        Set<Integer> hasCal = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 初始化改变后的面积初值
                count = 1;

                hasCal.clear(); // 每次使用时，清空
                if (grid[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nextx = i + direction[k][0];
                        int nexty = j + direction[k][1];
                        if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue;
                        // 使用过的岛屿
                        if (hasCal.contains(grid[nextx][nexty])) continue;
                        count += markSize.getOrDefault(grid[nextx][nexty], 0);
                        // 岛屿标记已使用
                        hasCal.add(grid[nextx][nexty]);
                    }
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }
}
