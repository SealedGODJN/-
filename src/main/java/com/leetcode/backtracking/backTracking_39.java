package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class backTracking_39 {

    /**
     * 记录结果
     */
    private List<List<Integer>> result;

    /**
     * 符合条件单一结果<br>
     * <p>
     * path这个数组的大小如果达到k，说明我们找到了一个子集大小为k的组合了，也说明到达叶子节点了<br>
     * 此时用result二维数组，把path保存起来，并终止本层递归
     */
    private List<Integer> path;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        path = new ArrayList<>();

        backTracking(candidates, 0, target, 0);
        return result;
    }

    public void backTracking(int[] candidates, int sum, int target, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            List<Integer> clone = new ArrayList<>();
            clone.addAll(path);
            result.add(clone);
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, sum, target, i);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1, 3, 4};
        int target = 13;

        backTracking_39 test = new backTracking_39();
        List<List<Integer>> result = test.combinationSum(candidates, target);
        System.out.println("result.size = " + result.size());

        for(List<Integer> one : result) {
            for(Integer i : one) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
