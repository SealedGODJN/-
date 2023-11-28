package com.leetcode.Graph.并查集;

import java.util.List;

public class 并查集_841 {
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

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j : rooms.get(i)) {
                if (find(j) == 0) continue;
                join(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            if (find(i) != 0) return false;
        }
        return true;
    }
}
