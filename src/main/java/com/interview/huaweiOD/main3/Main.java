package com.interview.huaweiOD.main3;

import java.util.*;

public class Main {
    // 2
    // 7 3 4 5 6 5 12 13
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // T组测试数据
        int T = sc.nextInt();
        // 存储数据
        for (int i = 0; i < T; i++) {
            List<Integer> vec = new ArrayList<>();
            int N = sc.nextInt(); // 测试数据长度
            for (int j = 0; j < N; j++) {
                int temp = sc.nextInt();
                vec.add(temp);
            }
            Collections.sort(vec);
            int count = maxCount(vec, 0);
            System.out.println(count);
        }
    }

    public static int maxCount(List<Integer> vec, int index) {
        int res = 0;
        for (int i = index; i < vec.size() - 2; i++) {
            if (vec.get(i) == 0)
                continue;
            for (int j = i + 1; j < vec.size() - 1; j++) {
                if (vec.get(j) == 0)
                    continue;
                for (int k = j + 1; k < vec.size(); k++) {
                    if (vec.get(k) == 0)
                        continue;
                    int a = vec.get(i);
                    int b = vec.get(j);
                    int c = vec.get(k);
                    // 回溯
                    if ((a * a) + (b * b) == (c * c)) {
                        vec.set(i, 0);
                        vec.set(j, 0);
                        vec.set(k, 0);
                        res = Math.max(res, maxCount(vec, i + 1) + 1);
                        vec.set(i, a);
                        vec.set(j, b);
                        vec.set(k, c);
                    }
                }
            }
        }
        return res;
    }
}
