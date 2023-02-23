package com.NPU.上海出差.网络演算法;

public class AFDXNetworkDelayUpperBound {
    public static void main(String[] args) {
        // 定义AFDX网络中节点数量
        int nodesNum = 8;
        // 定义AFDX网络中节点之间的距离
        int[][] distanceMatrix = new int[][]{{0, 5, 6, 7, 9, 10, 11, 12},
                {5, 0, 7, 8, 10, 11, 12, 13},
                {6, 7, 0, 9, 11, 12, 13, 14},
                {7, 8, 9, 0, 12, 13, 14, 15},
                {9, 10, 11, 12, 0, 14, 15, 16},
                {10, 11, 12, 13, 14, 0, 16, 17},
                {11, 12, 13, 14, 15, 16, 0, 18},
                {12, 13, 14, 15, 16, 17, 18, 0}};
        // 定义AFDX网络中节点之间的链路带宽
        int[][] bandwidthMatrix = new int[][]{{0, 10, 20, 30, 40, 50, 60, 70},
                {10, 0, 15, 25, 35, 45, 55, 65},
                {20, 15, 0, 40, 50, 60, 70, 80},
                {30, 25, 40, 0, 60, 70, 80, 90},
                {40, 35, 50, 60, 0, 80, 90, 100},
                {50, 45, 60, 70, 80, 0, 100, 110},
                {60, 55, 70, 80, 90, 100, 0, 120},
                {70, 65, 80, 90, 100, 110, 120, 0}};

        // 计算AFDX网络数据包延迟上界
        int delayUpperBound = 0;
        for (int i = 0; i < nodesNum; i++) {
            for (int j = 0; j < nodesNum; j++) {
                // 计算节点i到节点j的最小带宽
                int minBandwidth = Math.min(bandwidthMatrix[i][j], bandwidthMatrix[j][i]);
                // 计算节点i到节点j的距离
                int distance = distanceMatrix[i][j];
                // 计算节点i到节点j的延迟
                int delay = distance * minBandwidth;
                // 更新延迟上界
                delayUpperBound = Math.max(delayUpperBound, delay);
            }
        }
        // 输出AFDX网络数据包延迟上界
        System.out.println("AFDX Network Delay Upper Bound is: " + delayUpperBound);
    }
}