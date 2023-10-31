package com.leetcode.Graph.并查集;

import java.util.ArrayList;
import java.util.List;

public class 并查集_684 {
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

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;
        father = new int[n];
        for (int i = 1; i < n; i++) {
            father[i] = i;
        }

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
}
