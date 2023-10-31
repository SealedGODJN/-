package com.leetcode.Graph.并查集;

public class 并查集_1971 {
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

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }

        for (int[] edge : edges) {
            join(edge[0], edge[1]);
        }

        return isSame(source, destination);
    }
}
