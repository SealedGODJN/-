package com.interview.huawei20230426.main1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 模块总数N
        int N = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> modules = new ArrayList<>();

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            String line = scanner.nextLine();
            String[] nums = line.split(" ");
            int num = Integer.parseInt(nums[0]);
            if (num == 0) {
                queue.addLast(i);
                isVisited[i] = true;
            }
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                // 添加依赖项
                temp.add(Integer.parseInt(nums[j + 1]));
            }
            modules.add(temp);
        }

        int times = 0;
        while (!queue.isEmpty()) {
            int cur = queue.getFirst();
            queue.pop();
            for (int i = 0; i < modules.size(); i++) {
                for (Integer j : modules.get(i)) {
                    if (j == cur) {
                        queue.push(i);
                    }
                }
            }
            times++;
        }
        System.out.println(times);
    }
}
