package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class backTracking_216 {
    List<List<Integer>> result;

    List<Integer> path;

    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(n, k, 1);
        return result;
    }

    public void backTracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            int sum = path.stream().mapToInt(Integer::intValue).sum();
            if (sum == n) {
                result.add(new ArrayList<>(path));
                return;
            }

        }
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            // 递归
            backTracking(n, k, i + 1);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
