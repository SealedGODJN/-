package com.leetcode.Tree.FourWeek;

import com.leetcode.Tree.TreeNode;

public class Tree_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = null;
        if (root.val > p.val && root.val > q.val) {
            left = lowestCommonAncestor(root.left, p, q);
            if (left != null) {
                return left;
            }
        }
//        else if (root.val == p.val || root.val == q.val) {
//            return root;
//        }
        TreeNode right = null;
        if (root.val < p.val && root.val < q.val) {
            right = lowestCommonAncestor(root.right, p, q);
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
