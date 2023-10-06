package com.leetcode.Graph.DFS;

import java.util.ArrayList;
import java.util.List;

public class graph_210 {
    static List<List<Integer>> graph;

    boolean[] onPath;
    boolean[] visited;

    boolean hasCycle = false;

    int[] postorder;
    int count = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        postorder = new int[numCourses];
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
            // -1代表未被访问（DFS的visited[]数组）
            visited[i] = false;
        }
        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 0; j < prerequisites[i].length; j++) {
                // [0] 是 后 [1] 是 前
                graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (!hasCycle) dfs(i);
                // 存在环
            else return new int[]{};
        }
        int[] result = new int[numCourses];
        // 结果反转
        for (int i = numCourses - 1; i >= 0; i--) {
            result[numCourses - i - 1] = postorder[i];
        }
        return result;
    }

    public void dfs(int index) {
        // -1代表未被访问
        if (onPath[index]) {
            hasCycle = true;
        }
        if (visited[index] || hasCycle) {
            return;
        }

        // 已经被访问
        visited[index] = true;
        onPath[index] = true;
        for (int next : graph.get(index)) {
            // 不需要再判断是否被访问过，在递归函数的第一步，就设立了递归返回条件
            dfs(next);
        }
        postorder[count++] = index;
        onPath[index] = false;
    }
}
