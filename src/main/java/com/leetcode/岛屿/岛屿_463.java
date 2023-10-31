package com.leetcode.岛屿;

public class 岛屿_463 {
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += calculateEveryIsland(grid, i, j);
                }
            }
        }
        return result;
    }

    private int calculateEveryIsland(int[][] grid, int x, int y) {
        int result = 4;
        int nextx, nexty;
        nextx = x - 1;
        if (nextx >= 0) {
            if (grid[nextx][y] == 1) result--;
        }
        nextx = x + 1;
        if (nextx < grid.length) {
            if (grid[nextx][y] == 1) result--;
        }
        nexty = y - 1;
        if (nexty >= 0) {
            if (grid[x][nexty] == 1) result--;
        }
        nexty = y + 1;
        if (nexty < grid[0].length) {
            if (grid[x][nexty] == 1) result--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        岛屿_463 test = new 岛屿_463();
        System.out.println(test.islandPerimeter(grid));
    }
}
