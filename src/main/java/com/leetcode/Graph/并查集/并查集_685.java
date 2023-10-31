package com.leetcode.Graph.并查集;

import java.util.ArrayList;
import java.util.List;

public class 并查集_685 {

    int[] father;

    public int find(int u) {
        if (u == father[u]) return u;
        else return father[u] = find(father[u]);
    }

    public void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        father[v] = u;
    }

    public boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return true;
        return false;
    }

    public void init(int n) {
        father = new int[n];
        for (int i = 1; i < n; i++) {
            father[i] = i;
        }
    }

    // 图中存在入度为2的节点
    public boolean isTreeAfterRemoveEdge(int[][] edges, int deleteNode) {
        // 初始化
        int n = edges.length + 1;
        init(n);

        for (int i = 0; i < edges.length; i++) {
            // 这条边要删除
            if (i == deleteNode) continue;
            // 构成有向环了，一定不是树
            if (isSame(edges[i][0], edges[i][1])) return false;
            join(edges[i][0], edges[i][1]);
        }
        return true;
    }

    // 图中存在环
    public int[] getRemoveEdge(int[][] edges) {
        // 初始化
        int n = edges.length + 1;
        init(n);

        int[] result = new int[2];
        // 从前往后遍历
        for (int[] edge : edges) {
            if (isSame(edge[0], edge[1])) {
                result[0] = edge[0];
                result[1] = edge[1];
            } else {
                join(edge[0], edge[1]);
            }
        }
        return result;
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 初始化
        int n = edges.length + 1;

        int[] in = new int[n];
        // 初始化入度
        for (int[] edge : edges) {
            in[edge[1]]++;
        }

        // case12
        // 前两种入度为2的情况，一定是删除指向入度为2的节点的两条边其中的一条，
        // 同时注意要从后向前遍历，因为如果两天边删哪一条都可以成为树，就删最后那一条。
        List<Integer> in2 = new ArrayList<>();
        for (int i = edges.length - 1; i >= 0; i--) {
            if (in[edges[i][1]] == 2) in2.add(i);
        }

        // 如果有入度为2的节点，那么一定是两条边里删一个，看删哪个可以构成树
        if (in2.size() > 0) {
            if (isTreeAfterRemoveEdge(edges, in2.get(0))) {
                return edges[in2.get(0)];
            } else {
                return edges[in2.get(1)];
            }
        }
        // case3
        // 明确没有入度为2的情况，那么一定有有向环，找到构成环的边返回就可以了
        return getRemoveEdge(edges);
    }
}
