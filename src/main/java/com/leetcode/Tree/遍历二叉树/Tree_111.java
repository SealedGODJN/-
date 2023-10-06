package com.leetcode.Tree.遍历二叉树;

import com.leetcode.TreeNode;

public class Tree_111 {

    int maxDepth = Integer.MAX_VALUE;

    int depth = 0;

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        traverse(root);
        return maxDepth;
    }

    public void traverse(TreeNode root) {
        if (root != null) {
            // 要放在返回之前
            depth++;
            if (root.left == null && root.right == null) {
                maxDepth = Math.min(maxDepth, depth);
            }
//            depth++;
            // 回溯解法
            traverse(root.left);
            traverse(root.right);
            depth--;
        }
    }
}
