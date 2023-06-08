package com.leetcode.Tree.遍历二叉树;

import com.leetcode.Tree.TreeNode;

public class Tree_543 {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    public int maxDepth(TreeNode root) {
        if (root != null) {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            if (left + right > max) max = left + right;
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}
