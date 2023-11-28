package com.leetcode.Graph.BFS;

import com.leetcode.Graph.DFS.graph_695;

import java.util.Deque;
import java.util.LinkedList;

public class graph_1020 {

    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            bfs(grid, i, 0);
        }
        for (int i = 0; i < grid[0].length; i++) {
            bfs(grid, 0, i);
        }
        for (int i = 0; i < grid.length; i++) {
            bfs(grid, i, grid[0].length - 1);
        }
        for (int i = 0; i < grid[0].length; i++) {
            bfs(grid, grid.length - 1, i);
        }

        for (int[] ints : grid) {
            for (int anInt : ints) {
                count += anInt;
            }
        }
        return count;
    }

    private boolean inArea(int[][] grid, int i, int j) {
        return i >= 0 && i <= grid.length - 1 && j >= 0 && j <= grid[0].length - 1;
    }

    /**
     * 理解bfs：<br>
     * 1）辅助队列<br>
     * 2）广度优先遍历（下一个搜索的是？根据数据结构关系，树形、二维、三维、图）<br>
     * 3）设置条件，在遍历中收集结果<br>
     *
     * @param grid
     * @param i
     * @param j
     */
    private void bfs(int[][] grid, int i, int j) {
        if (grid[i][j] == 0) return;
        // 判断搜索的位置是否合法
        Deque<int[]> q = new LinkedList<>();
        q.push(new int[]{i, j});
        grid[i][j] = 0;

        while (!q.isEmpty()) {
            int[] temp = q.pop();
            int curX = temp[0];
            int curY = temp[1];
            for (int k = 0; k < 4; k++) {
                int nextX = curX + direction[k][0];
                int nextY = curY + direction[k][1];
                if (!inArea(grid, nextX, nextY)) continue;
                if (grid[nextX][nextY] == 1) {
                    grid[nextX][nextY] = 0;
                    q.push(new int[]{nextX, nextY});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1}
        };

        graph_1020 graph200 = new graph_1020();
        System.out.println(graph200.numEnclaves(grid));
    }
}
