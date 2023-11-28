package com.leetcode.Graph.并查集;

public class 模板_rank {

    /**
     * father的长度
     */
    private int size;

    /**
     * 并查集
     */
    private int[] father;

    private int[] rank;

    public void init() {
        for (int i = 0; i < father.length; i++) {
            // 每个节点指向自己
            father[i] = i;

            // 初始树的高度为1
            rank[i] = 1;
        }
    }

    public void join(int u, int v) {
        u = find(u);
        v = find(v);
//        if (rank[u] <= rank[v]) {
//            father[u] = v;
//            rank[v]++;
//        }
//        father[v] = u;
//        rank[u]++;

        // 先设置根
        if (rank[u] <= rank[v]) {
            father[u] = v;
        } else {
            father[v] = u;
        }

        // 如果两棵树高度相同，则v的高度+1
        // 因为，方面 if (rank[u] <= rank[v]) father[u] = v; 注意是 <=
        if (rank[u] == rank[v] && u != v) rank[v]++;
    }

    public int find(int u) {
        return u == father[u] ? u : find(father[u]);// 注意这里不做路径压缩
    }

    public boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }
}
