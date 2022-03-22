package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Tree_113 {
    List<List<Integer>> allPath = new ArrayList<>();

    List<Integer> path = new ArrayList<>();

    /**
     * 返回所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     *
     * @param root      树的根节点
     * @param targetSum 目标和
     * @return 所有满足目标和的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return allPath;
        }

        path.add(root.val);
        traversal(root, root.val, targetSum);
        return allPath;
    }

    /**
     * 递归遍历树的每一个节点
     *
     * @param root      树的节点
     * @param sum       记录当前路径节点值的和
     * @param targetSum 路径节点值的和的目标值
     */
    public void traversal(TreeNode root, int sum, int targetSum) {

        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                allPath.add(new ArrayList<>(path));
                return;
            }
        }

        if (root.left != null) {
            path.add(root.left.val);
            traversal(root.left, sum + root.left.val, targetSum);
            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
            traversal(root.right, sum + root.right.val, targetSum);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
//        TreeNode root_left_left = new TreeNode(4);
//        TreeNode root_left_right = new TreeNode(5);
//        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
        TreeNode root_left = new TreeNode(1, null, null);

//        TreeNode root_right_left = new TreeNode(6);
//        TreeNode root_right_right = new TreeNode(7);
//        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);
        TreeNode root_right = new TreeNode(1, null, null);

        TreeNode root = new TreeNode(0, root_left, root_right);

        Tree_113 tree_94 = new Tree_113();
        List<List<Integer>> result = tree_94.pathSum(root, 1);
        for (List<Integer> path : result) {
            System.out.print("[");
            for (Integer integer : path) {
                System.out.print(integer + " ");
            }
            System.out.println("]");
        }
    }
}
