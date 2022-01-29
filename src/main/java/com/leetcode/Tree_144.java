package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_144 {
    /**
     * 树的前序遍历
     * @param root 一棵树的根节点
     * @return 树的前序遍历的结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    /**
     * 递归遍历树
     * @param root 一棵树的根节点
     * @param result 树的前序遍历的结果
     */
    public void traversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traversal(root.left, result);
        traversal(root.right, result);
    }

    /**
     * 以非递归的方式遍历
     * @param root 待遍历待树节点
     * @return 如果树节点不为空，则返回该树待前序遍历的结果<br></>
     *         如果树节点为空，则返回空的数组
     */
    public List<Integer> preorderTraversal_Non_Recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> needToTraversal = new Stack<>();
        needToTraversal.push(root);
        while (!needToTraversal.isEmpty()) {
            TreeNode visitNode = needToTraversal.pop();
            result.add(visitNode.val);

            if (visitNode.right != null) {
                needToTraversal.add(visitNode.right);
            }
            if (visitNode.left != null) {
                needToTraversal.add(visitNode.left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        TreeNode root_left_left = new TreeNode(4);
//        TreeNode root_left_right = new TreeNode(5);
//        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
//
//
//        TreeNode root_right_left = new TreeNode(6);
//        TreeNode root_right_right = new TreeNode(7);
//        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

        TreeNode root_left = new TreeNode(1);
        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(3, root_left, root_right);

        Tree_144 tree_144 = new Tree_144();
        List<Integer> result  = tree_144.preorderTraversal(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
    }
}
