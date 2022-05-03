package com.leetcode.Tree.FiveWeek;

import com.leetcode.Tree.TreeNode;

public class Tree_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = null;
        if (root.val > p.val && root.val > q.val) { // p 和 q 都在左子树
            left = lowestCommonAncestor(root.left, p, q); // 递归
            if (left != null) {
                return left;
            }
        }
//        else if (root.val == p.val || root.val == q.val) {
//            return root;
//        }
        TreeNode right = null;
        if (root.val < p.val && root.val < q.val) { // p 和 q 都在右子树
            right = lowestCommonAncestor(root.right, p, q); // 递归
            if (right != null) {
                return right;
            }
        }
//        else if (root.val == p.val || root.val == q.val) {
//            return root;
//        }
        return root;
    }
}
