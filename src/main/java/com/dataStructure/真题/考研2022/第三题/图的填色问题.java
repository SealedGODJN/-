package com.dataStructure.真题.考研2022.第三题;

public class 图的填色问题 {
    public static int[] color;

    /**
     * @param n N种颜色
     * @param m M个国家
     * @param c 邻接矩阵
     */
    public static void graphcolor(int n, int m, int[][] c) {
        // 初始化color
        color = new int[m];
        for (int i = 0; i < m; i++) {
            // 设置每个国家的颜色为无
            color[i] = 0;
        }
        // 第k个国家
        int k = 0;
        while (k >= 0) {
            // 不同国家设置不同的颜色
            color[k] = color[k] + 1;
            while (color[k] <= n) {
                if (ok(k, c, m)) {
                    break;
                } else {
                    // 寻找下一个颜色
                    color[k]++;
                }
            }
            if (color[k] <= n && k == m - 1) {
                // 成功处理完所有顶点
                for (int i = 0; i < m; i++) {
                    System.out.println(color[i]);
                }
                break;
            } else if (color[k] <= n && k < m - 1) {
                k = k + 1; // 处理下一个顶点
            } else {
                // 所有颜色都无法满足
                // 则回溯
                color[k] = 0;
                k = k - 1; // 回溯
            }
        }

    }

    /**
     * 判断k和其所有相邻的国家是否有相同的颜色
     * 【要从二维数组k这一行入手，找到与k相邻的国家，判断是否】
     *
     * @param k 当前国家的数组下标
     * @param c 邻接矩阵
     * @param m M个国家
     * @return
     */
    private static boolean ok(int k, int[][] c, int m) {
        for (int i = 0; i < m; i++) {
            if (c[k][i] == 1 && color[i] == color[k]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 4种颜色
        int n = 4;
        // 6个国家
        int m = 7;
        // 存储n个顶点的邻接矩阵
        int[][] edges = new int[m][m];
        edges[0] = new int[]{0, 1, 1, 0, 1, 0, 0};
        edges[1] = new int[]{1, 0, 0, 0, 1, 0, 0};
        edges[2] = new int[]{1, 0, 0, 1, 1, 0, 0};
        edges[3] = new int[]{0, 0, 1, 0, 1, 1, 1};
        edges[4] = new int[]{1, 1, 1, 1, 0, 1, 1};
        edges[5] = new int[]{0, 0, 0, 1, 1, 0, 1};
        edges[6] = new int[]{0, 0, 0, 1, 1, 1, 0};
        graphcolor(n, m, edges);
    }
}

class vertexType {
    /**
     * 顶点编号
     */
    int no;

    /**
     * 顶点其他信息
     */
    char info;
}

/**
 * 邻接矩阵表示法
 */
class MGraph {
    /**
     * 邻接矩阵
     */
    int[][] edges;

    /**
     * 顶点数
     */
    int n;

    /**
     * 边数
     */
    int e;

    /**
     * 存放顶点信息
     */
    vertexType[] vex;

    public MGraph(int[][] edges, int n, int e, vertexType[] vex) {
        this.edges = edges;
        this.n = n;
        this.e = e;
        this.vex = vex;
    }
}