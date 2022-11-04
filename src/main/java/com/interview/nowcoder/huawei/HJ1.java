package com.interview.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ1 {
    static int n; // 棋盘的长和宽
    static String[][] chess; // 棋盘上的棋子
    static boolean[][] B; // 当前位置是否有气
    static int[][] airNums; // 当前位置，气的数量

    public static void main(String[] args) throws IOException {
        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        n = Integer.parseInt(str.substring(0, 1));
        chess = new String[n][n];
        for (int i = 0; i < n; i++) {
            String temp = str.substring(1 + n * i, 1 + n + n * i);
            for (int j = 0; j < n; j++) {
                chess[i][j] = temp.substring(j, j + 1);
            }
        }
        B = new boolean[n][n];
        airNums = new int[n][n];

        // please finish the function body here.
        int black = 0;
        int white = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String type = chess[i][j];
                boolean isALive = hasAir(i, j, type);
                if (isALive) {
                    if (type.equals("W")) white++;
                    else if (type.equals("B")) black++;
                }
            }
        }
        System.out.print("B " + black + " W " + white);

        // please define the JAVA output here. For example: System.out.println(s.nextInt());
    }

    public static boolean hasAir(int i, int j, String type) {
        if (chess[i][j].equals("N")) return true;
        if (!chess[i][j].equals(type)) return false;

        B[i][j] = true;
        if (i > 0 && !B[i - 1][j] && hasAir(i - 1, j, type)) airNums[i][j]++;
        if (i < n - 1 && !B[i + 1][j] && hasAir(i + 1, j, type)) airNums[i][j]++;
        if (j > 0 && !B[i][j - 1] && hasAir(i, j - 1, type)) airNums[i][j]++;
        if (j < n - 1 && !B[i][j + 1] && hasAir(i, j + 1, type)) airNums[i][j]++;

        if (airNums[i][j] >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasAirOfType(String type, int p, int q) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!chess[i][j].equals(type) || B[i][j] || airNums[i][j] >= 2) {
                    continue;
                }
                if (!hasAir(i, j, type)) {
                    p = i;
                    q = j;
                    return false;
                }
            }
        }
        return true;
    }

}
