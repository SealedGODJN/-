package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class backTracking_77 {

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

    /**
     * 1、递归函数的返回值以及参数<br>
     * 在这里要定义两个全局变量，一个用来存放符合条件单一结果，一个用来存放符合条件结果的集合。<br>
     * <p>
     * 2、终止条件
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        backTracking(n, k, 1);
        return result;
    }

    /**
     * startIndex来记录下一层递归，搜索的起始位置
     *
     * @param n
     * @param k
     * @param startIndex
     */
    public void backTracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            // 递归
            backTracking(n, k, i + 1);
            // 回溯
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        backTracking_77 backTracking77 = new backTracking_77();
        backTracking77.combine(4, 2);
        for (List<Integer> list : backTracking77.result) {
            System.out.print("[");
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
    }
}
