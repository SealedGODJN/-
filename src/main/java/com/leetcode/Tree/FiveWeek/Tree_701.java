package com.leetcode.Tree.FiveWeek;

import com.leetcode.Tree.TreeNode;

public class Tree_701 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
//    public TreeNode insertIntoBST(TreeNode root, int val) {
//        if (root == null) {
//            root = new TreeNode(val);
//            return root;
//        }
//        else if (root.val > val) {
//            root.left = insertIntoBST(root.left, val);
//            return root;
//        } else {
//            root.right = insertIntoBST(root.right, val);
//            return root;
//        }
//    }

    /**
     * 迭代法
     * 基于700：二叉搜索树的搜索【迭代法】
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode parent = root;
        while (cur != null) {
            parent = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else if (cur.val < val) {
                cur = cur.right;
            }
        }
        TreeNode temp = new TreeNode(val);
        if (parent.val > val) parent.left = temp;
        else parent.right = temp;
        return root;
    }

}
