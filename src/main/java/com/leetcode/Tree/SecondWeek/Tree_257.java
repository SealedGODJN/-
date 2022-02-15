package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.FirstWeek.Traverse.Tree_94;
import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Tree_257 {
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 1、明确递归函数的参数和返回值 <br>
     * 参数：node
     * 返回值：
     *
     * 2、明确终止条件 <br>
     *
     *
     * 3、单层逻辑 <br>
     *
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> allPath = new ArrayList<>();
        if (root == null) {
            return allPath;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);

        traversal(root, path);
        for (List<Integer> paths : result) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : paths) {
                sb.append(integer).append("->");
            }
            sb.delete(sb.length() - 2, sb.length());
            allPath.add(sb.toString());
        }
        return allPath;
    }

    /**
     * 递归遍历树
     * @param root 一棵树的根节点
     * @param path 根节点到叶子节点
     */
    public void traversal(TreeNode root, List<Integer> path) {
        if (root.left == null && root.right == null) {
            List<Integer> temp = new ArrayList<>(path);
            result.add(temp);
            return;
        }
//        path.add(root.val);

        if (root.left != null) {
            path.add(root.left.val);
            traversal(root.left, path);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            traversal(root.right, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);

        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_257 tree_94 = new Tree_257();
        List<String> result  = tree_94.binaryTreePaths(root);
        for (String integer : result) {
            System.out.print(integer + " ");
        }
    }
}
