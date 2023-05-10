package com.interview.huaweiOD.main2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] nums = line.split(" ");
        if (nums.length == 1) {
            System.out.println(0);
            return;
        }
        List<Integer> f = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].equals("1")) {
                sum += 1;
            } else if (nums[i].equals("0")) {
                if (sum != 0)
                    f.add(sum);
                sum = 0;
                // 空位
                f.add(0);
            } else if (nums[i].equals("2")) {
                if (sum != 0)
                    f.add(sum);
                sum = 0;
                // 障碍物
                f.add(-1);
            }
            if (i == nums.length - 1 && sum != 0) {
                f.add(sum);
            }
        }
        // 最大友好度
        int friend = 0;
        for (int i = 0; i < f.size(); i++) {
            if (f.get(i) == 0 && i == 0) {
                if (f.get(i + 1) != -1) friend = Math.max(friend, f.get(i + 1));
            } else if (f.get(i) == 0 && i == f.size() - 1) {
                if (f.get(i - 1) != -1) friend = Math.max(friend, f.get(i - 1));
            } else if (f.get(i) == 0 && i != 0 && i != f.size() - 1) {
                if (f.get(i - 1) != -1 && f.get(i + 1) != -1) {
                    friend = Math.max(friend, f.get(i - 1) + f.get(i + 1));
                } else if (f.get(i - 1) == -1 && f.get(i + 1) != -1) {
                    friend = Math.max(friend, f.get(i + 1));
                } else if (f.get(i - 1) != -1 && f.get(i + 1) == -1) {
                    friend = Math.max(friend, f.get(i - 1));
                }
            }
        }
        System.out.println(friend);
    }
}
