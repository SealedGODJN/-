package com.leetcode.Tree;

import java.util.Set;
import java.util.HashSet;

public class Tree_653 {
//    Set<Integer> find = new HashSet<>();
//
//    // 没有利用到二叉搜索树的特性！
//    public boolean findTarget(TreeNode root, int k) {
//        if (root != null) {
//            // 终止条件
//            if (find.contains(k - root.val)) {
//                return true;
//            }
//            find.add(root.val);
//            return findTarget(root.left, k) || findTarget(root.right, k);
//        }
//        return false;
//    }

    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    /**
     * dfs 搜索cur
     * @param root
     * @param cur
     * @param k
     * @return
     */
    private boolean dfs(TreeNode root, TreeNode cur, int k) {
        if (cur == null) return false;
        else return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    /**
     * 搜索二叉搜索树（根据二叉搜索树的特性）
     * @param root
     * @param cur
     * @param target
     * @return
     */
    private boolean search(TreeNode root, TreeNode cur, int target) {
        if (root == null) return false;
        if (root.val == target && root != cur) return true;
        if (root.val > target) return search(root.left, cur, target);
        return search(root.right, cur, target);
    }
}
