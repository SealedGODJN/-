package com.interview.alibaba.h20230913.main3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<List<Integer>> graph;

    static List<Integer> res;

    /**
     * 传入的x，day，代表x在第day天失去联系（通过DFS，找到与x相连的节点，同样会失去联系的节点）
     *
     * @param x   x号节点
     * @param day 从第一天开始
     */
    public static void DFS(int x, int day) {
        // -1代表未被访问
        if (res.get(x) != -1) {
            // 已经失去连接
            return;
        }
        // 失去连接（同时已经被访问）
        res.set(x, day);
        for (int v : graph.get(x)) {
            // 不需要再判断是否被访问过，在递归函数的第一步，就设立了递归返回条件
            DFS(v, day);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 树的节点数
        int n = scanner.nextInt();
        // 天数
        int k = scanner.nextInt();
        graph = new ArrayList<>(n);
        res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            // -1代表未被访问（DFS的visited[]数组）
            // 默认都是无连接
            res.add(-1);
        }
        while (n != 1) {
            n--;
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            // 邻接表方式存储
            graph.get(u - 1).add(v - 1);
        }
        int day = 1;
        while (k-- > 0) {
            int x = scanner.nextInt();
            DFS(x - 1, day);
            day++;
        }
        for (int i = 1; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}

