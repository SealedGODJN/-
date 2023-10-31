package com.leetcode.Graph.并查集;

import java.util.*;

public class 模板 {

    /**
     * father的长度
     */
    private int size;

    /**
     * 并查集
     */
    private int[] father;

    public void init() {
        for (int i = 0; i < father.length; i++) {
            // 每个节点指向自己
            father[i] = i;
        }
    }

    public void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        father[u] = v;
    }

    public int find(int u) {
        if (u == father[u]) return u;
        else {
            father[u] = find(father[u]);
            return father[u];
        }
    }

    public boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }

    public static void main(String[] args) {
        模板 test = new 模板();
        test.size = 10;
        test.father = new int[test.size];
    }
}
