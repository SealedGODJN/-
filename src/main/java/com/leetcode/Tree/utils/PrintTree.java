package com.leetcode.Tree.utils;

import com.leetcode.TreeNode;

public class PrintTree {
    static String prefix = "-";

    /**
     * 以-的数量来指出节点在二叉树中的层数
     *
     * @param root
     */
    public static void printBinaryTree(TreeNode root) {
        if (root == null) return;
        System.out.println(prefix + root.val);

        if (root.left != null) {
            prefix += "-";
            printBinaryTree(root.left);
            prefix = prefix.substring(0, prefix.length() - 1);
        }
        if (root.right != null) {
            prefix += "-";
            printBinaryTree(root.right);
            prefix = prefix.substring(0, prefix.length() - 1);
        }

    }
}
