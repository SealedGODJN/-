package com.leetcode.dp;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class dp_337 {

    int sum = 0;

    /**
     * 树形dp的入门题
     * <p>
     * 1、确定递归函数的参数和返回值
     * dp[i]的含义
     * dp数组（dp table）以及下标的含义：下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。
     * <p>
     * 2、确定递归函数的参数和返回值
     * public List<Integer> robTree(TreeNode root) {
     * <p>
     * 3、确定遍历顺序
     * 树的后序遍历
     * <p>
     * 4、确定单层递归的逻辑
     * 计算偷和不偷当前节点的最大金钱
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        List<Integer> result = robTree(root);
        return Math.max(result.get(0), result.get(1));
    }

    /**
     * 下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。
     *
     * @param root
     * @return
     */
    public List<Integer> robTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(0);
        if (root != null) {
            // 通过递归左节点，得到左节点偷与不偷的金钱。
            List<Integer> left = robTree(root.left);

            // 通过递归右节点，得到右节点偷与不偷的金钱。
            List<Integer> right = robTree(root.right);

            int val1 = root.val + left.get(0) + right.get(0);
            // 不偷cur，那么可以偷也可以不偷左右节点，则取较大的情况
            int val2 = Math.max(left.get(0), left.get(1)) + Math.max(right.get(0), right.get(1));

            // 不偷左孩子和右孩子
            result.set(0, val2);
            result.set(1, val1);
            return result;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);


        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

        TreeNode root = new TreeNode(3, root_left, root_right);

        dp_337 dp_337 = new dp_337();
        System.out.println(dp_337.rob(root));
    }
}
