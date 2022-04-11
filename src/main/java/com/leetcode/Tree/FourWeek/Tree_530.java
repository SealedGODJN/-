package com.leetcode.Tree.FourWeek;

import com.leetcode.Tree.TreeNode;

import java.util.Stack;

public class Tree_530 {
//    int miniDiff = Integer.MAX_VALUE;
//    public int getMinimumDifference(TreeNode root) {
//        if (root == null) {
//            return -1;
//        }
//        int left = getMinimumDifference(root.left);
//        if (pre != null && pre.val != -1) {
//            int temp = root.val - pre.val;
//            if ( temp < miniDiff ) {
//                miniDiff = temp;
//            }
//        }
//        pre = root;
//
//        int right = getMinimumDifference(root.right);
//
//        return miniDiff;
//    }
//    TreeNode pre = null; // 用来记录前一个节点

    int miniDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> record = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null || !record.isEmpty()) {
            if (cur != null) {
                record.push(cur);
                cur = cur.left; // 左
            } else {
                cur = record.pop(); // 中
                if (pre != null && pre.val != -1) {
                    int temp = cur.val - pre.val;
                    if (temp < miniDiff) {
                        miniDiff = temp;
                    }
                }
                pre = cur;
                cur = cur.right; // 右
            }
        }
        return miniDiff;
    }
}
