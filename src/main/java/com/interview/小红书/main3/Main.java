package com.interview.小红书.main3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        StringBuilder out = new StringBuilder();
        // 处理一组，输出一次
        for (int i = 0; i < T; i++) {
            //
            boolean q1 = true;
            boolean q2 = false;
            boolean q3 = false;


            int n = sc.nextInt(); // 袋子数量
            int[] c = new int[n]; // 每个袋子的球数
            Set<Integer> judgeQ2 = new TreeSet<>();
            sc.nextLine();
            Map<Integer, Integer> judgeQ3 = new HashMap<>();
            // 每个袋子
            for (int j = 0; j < n; j++) {
                Set<Integer> judgeQ1 = new TreeSet<>();
                // 每个袋子中球的数量
                c[j] = sc.nextInt();
                judgeQ2.add(c[j]);
                int[] nums = new int[c[j]];
                // 袋子中的所有球
                for (int k = 0; k < c[j]; k++) {
                    nums[k] = sc.nextInt();
                    judgeQ1.add(nums[k]);

                    // 3、同一颜色的球在一个袋子中出现，或者在所有袋子出现
                    if (judgeQ3.containsKey(nums[k])) {
                        judgeQ3.replace(nums[k], judgeQ3.get(nums[k]) + 1);
                    } else {
                        judgeQ3.put(nums[k], 1);
                    }
                }
                sc.nextLine();

                // 1、每次球的颜色要两两相同
                if (judgeQ1.size() != c[j]) {
                    q1 = false;
                }
            }

            // 2、每个袋子的球数量相同
            if (judgeQ2.size() == 1) {
                q2 = true;
            }

            // 3、同一颜色的球在一个袋子中出现，或者在所有袋子出现
            // 3、同一颜色的球在所有袋子出现，则最后要输出该球的号码
            List<Integer> result = new ArrayList<>();
            for (Integer key : judgeQ3.keySet()) {
                if (judgeQ3.get(key) >= n) {
                    q3 = true;
                    result.add(key);
                } else if (judgeQ3.get(key) == 1) {
                    q3 = true;
                }
            }

            if (q1 && q2 && q3) {
                out.append("Yes").append("\n");
                StringBuilder sb = new StringBuilder();
                if (result.size() != 0) {
                    for (Integer key : result) {
                        sb.append(key).append(" ");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    System.out.println(sb);
                    out.append(sb).append("\n");
                }
            } else {
                out.append("No").append("\n");
            }
        }
        System.out.println(out);
    }
}
