package com.interview.huawei.huaweiOD;

import java.util.Scanner;

public class 最短木板长度_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        String[] nAndm = line1.split(" ");
        int n = Integer.parseInt(nAndm[0]);
        int m = Integer.parseInt(nAndm[1]);

        String[] woods = line2.split(" ");
        int[] woodsInt = new int[n];
        for (int i = 0; i < n; i++) {
            woodsInt[i] = Integer.parseInt(woods[i]);
        }
        int min = Integer.MAX_VALUE;
        while (m != 0) {
            boolean end = true;
            for (int i = 0; i < n; i++) {
                int diff = woodsInt[(i + 1) % 5] - woodsInt[i];
                if (diff > 0) {
                    end = false;
                    if (diff < m) {
                        woodsInt[i] += diff;
                        m -= diff;
                    } else {
                        woodsInt[i] += m;
                        m = 0;
                        end = true;
                    }
                    min = Math.min(min, woodsInt[i]);
                }
            }
            if (end) break;
        }
        System.out.println(min);
    }
}
