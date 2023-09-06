package com.leetcode.backtracking;

import java.util.*;

public class backTracking_40 {

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
    private LinkedList<Integer> path;

    /**
     * 树层去重
     *
     * used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
     * used[i - 1] == false，说明同一树层candidates[i - 1]使用过
     */
    private boolean[] used;

    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        path = new LinkedList<>();
        used = new boolean[candidates.length];
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        Arrays.fill(used, false);
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTracking(candidates, target, 0);
        return result;
    }

    private void backTracking(int[] candidates, int target, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            sum += candidates[i];
            path.add(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            backTracking(candidates, target, i + 1);
            used[i] = false;
            sum -= candidates[i];
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;

        backTracking_40 test = new backTracking_40();
        List<List<Integer>> result = test.combinationSum2(candidates, target);
        System.out.println("result.size = " + result.size());

        for(List<Integer> one : result) {
            for(Integer i : one) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
