package com.leetcode.Graph.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph_797 {

    /**
     * 记录从起点到当前节点的路径
     */
    LinkedList<Integer> path = new LinkedList<>();

    List<List<Integer>> result = new ArrayList<>();

    /* 图遍历框架 */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
//        path.add(0);
        dfs(graph, 0);
        return result;
    }

    private void dfs(int[][] graph, int curVertex) {
        // 做选择：标记节点 s 在路径上
        path.add(curVertex);
        if (curVertex == graph.length - 1) {
            result.add(new ArrayList<>(path));
            // 可以在这直接 return，但要 removeLast 正确维护 path
            // path.removeLast();
            // return;
            // 不 return 也可以，因为图中不包含环，不会出现无限递归
        }
        for (int i : graph[curVertex]) {
            dfs(graph, i);
        }
        // 撤销选择：节点 s 离开路径
        path.removeLast();
    }

    public static void main(String[] args) {
        int[][] graph = {
                {1,2},
                {3},
                {3},
                {}
        };

        Graph_797 graph797 = new Graph_797();
        List<List<Integer>> result = graph797.allPathsSourceTarget(graph);
        for (List<Integer> path:
             result) {
            for (Integer i:
                 path) {
                System.out.print(i + "->");
            }
            System.out.println();
        }
    }
}
