package com.interview.huawei.huawei20230510.main3;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int INF = 0x3f3f3f3f;

    // 最小堆写法不熟
//    class Edge {
//        int
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine());
        int edgeLength = Integer.parseInt(sc.nextLine());
        int[][] edge = new int[m + 1][m + 1];
//        List<Edge>
        for (int i = 0; i < edgeLength; i++) {
//            for (int j = 0; j < 3; j++) {
            String line = sc.nextLine();
            String[] nums = line.split(" ");
//                int first = sc.nextInt();
//                int end = sc.nextInt();
//                int cost = sc.nextInt();
            int first = Integer.parseInt(nums[0]);
            int end = Integer.parseInt(nums[1]);
            int cost = Integer.parseInt(nums[2]);
            edge[first][end] = cost;
//            }
        }
        int source = sc.nextInt();
//        PriorityQueue<>
        int[] cost = new int[m + 1];
        for (int i = 0; i < m + 1; i++) {
            cost[i] = INF;
        }
        cost[source] = 0;
        boolean[] isVisited = new boolean[m + 1];

        for (int i = 1; i <= m; i++) {
            int cur = -1;
            int min = INF;
            for (int j = 1; j <= m; j++) {
                if (!isVisited[j] && cost[j] < min) {
                    min = cost[j];
                    cur = j;
                }
            }
            if (cur == -1)
                continue;
            isVisited[cur] = true;
            for (int j = 1; j < m + 1; j++) {
                if (!isVisited[j] && edge[cur][j] != 0 && cost[j] > cost[cur] + edge[cur][j] + edge[j][j]) {
                    cost[j] = cost[cur] + edge[cur][j] + edge[j][j];
                }
            }
        }
        int maxTime = 0;
        int count = 0;
        for (int i = 1; i < m + 1; i++) {
            if (cost[i] != INF) {
                count++;
                if (cost[i] > maxTime) {
                    maxTime = cost[i];
                }
            }
        }
        System.out.println(count);
        System.out.println(maxTime);
    }
}
