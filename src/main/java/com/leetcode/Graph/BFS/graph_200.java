//package com.leetcode.Graph.BFS;
//
//import javafx.util.Pair;
//
//import java.util.Deque;
//import java.util.LinkedList;
//
//public class graph_200 {
//
//    public int numIslands(char[][] grid) {
//        int count = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                if (grid[i][j] == '1') {
//                    count++;
//                    bfs(grid, i, j);
//                }
//            }
//        }
//        return count;
//    }
//
//    private void bfs(char[][] grid, int i, int j) {
//        Deque<Pair<Integer, Integer>> s = new LinkedList<>();
//
//        s.push(new Pair<>(i, j));
//        // 已访问过
//        grid[i][j] = '2';
//
//        while (!s.isEmpty()) {
//            int size = s.size();
//            for (int k = 0; k < size; k++) {
//                Pair<Integer, Integer> p = s.pop();
//                i = p.getKey();
//                j = p.getValue();
//                if (i - 1 >= 0 && grid[i - 1][j] == '1') {
//                    s.push(new Pair<>(i - 1, j));
//                    grid[i - 1][j] = '2';
//                }
//                if (i + 1 < grid.length && grid[i + 1][j] == '1') {
//                    s.push(new Pair<>(i + 1, j));
//                    grid[i + 1][j] = '2';
//                }
//                if (j - 1 >= 0 && grid[i][j - 1] == '1') {
//                    s.push(new Pair<>(i, j - 1));
//                    grid[i][j - 1] = '2';
//                }
//                if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
//                    s.push(new Pair<>(i, j + 1));
//                    grid[i][j + 1] = '2';
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
//
//        graph_200 graph200 = new graph_200();
//        System.out.println(graph200.numIslands(grid));
//    }
//}
