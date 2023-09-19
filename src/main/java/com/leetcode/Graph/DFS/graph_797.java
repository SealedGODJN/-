package com.leetcode.Graph.DFS;

import java.util.ArrayList;
import java.util.List;

public class graph_797 {
    List<List<Integer>> result = new ArrayList<>();

    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int target = graph.length - 1;
        dfs(0, target, graph);
        return result;
    }

    // dfs 本质是 回溯
    public void dfs(int index, int target, int[][] graph) {
        if (index == target) {
            path.add(target);
            result.add(new ArrayList(path));
            path.remove(path.size() - 1);
        }
        for (int i = 0; i < graph[index].length; i++) {
            path.add(index);

            dfs(graph[index][i], target, graph);

            path.remove(path.size() - 1);
        }
    }
}
