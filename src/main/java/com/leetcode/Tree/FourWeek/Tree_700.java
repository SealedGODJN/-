package com.leetcode.Tree.FourWeek;

import com.leetcode.TreeNode;

public class Tree_700 {
//    /**
//     * 二叉搜索树搜索val
//     * 递归法求解
//     * @param root 根节点
//     * @param val 目标值
//     * @return 该节点的值==val
//     */
//    public TreeNode searchBST(TreeNode root, int val) {
//        if (root == null) {
//            return null;
//        }
//        if (root.val == val) {
//            return root;
//        }
//        if (root.val > val) {
//
//            return searchBST(root.left, val);
//        } else {
//            return searchBST(root.right, val);
//        }
//    }

    /**
     * 迭代法，不需要辅助栈或者辅助队列
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.val == val) {
                return temp;
            } else if (temp.val < val) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return null;
    }
}
