package com.interview.huawei.huawei0519;

public class Solution {
    static int[][] arr = new int[][]{{1, 0, 0, 0, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1, 1}};

    public static void main(String[] args) {
        /**
         * 伪代码思路:
         * 用1标记油田，用0标记空地，用2标记已遍历过的油田
         */
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    int num = DFS(i, j, 0);
                    //深度遍历所有的油田。深度遍历的过程中，每访问一个节点，标记为2，并累计面积
                    //本次深度遍历结束，说明访问完一块油田
                    max = Math.max(max, num);
                }
            }
        }
        System.out.println(max);
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static int DFS(int i, int j, int count) {
        if (arr[i][j] == 1) {
            arr[i][j] = 2;
            count++;
            if (i > 0) {
                count = count + DFS(i - 1, j, 0);
            }
            if (i < arr.length - 1) {
                count = count + DFS(i + 1, j, 0);
            }
            if (j > 0) {
                count = count + DFS(i, j - 1, 0);
            }
            if (j < arr[i].length - 1) {
                count = count + DFS(i, j + 1, 0);
            }
        } else {
            return 0;
        }
        return count;
    }
}