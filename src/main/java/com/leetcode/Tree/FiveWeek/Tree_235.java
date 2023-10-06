package com.leetcode.Tree.FiveWeek;

import com.leetcode.TreeNode;

public class Tree_235 {
//
//    /**
//     * 递归法
//     * 后序遍历
//     * @param root
//     * @param p
//     * @param q
//     * @return
//     */
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) {
//            return null;
//        }
//        TreeNode left = null;
//        if (root.val > p.val && root.val > q.val) { // p 和 q 都在左子树
//            left = lowestCommonAncestor(root.left, p, q); // 递归
//            if (left != null) {
//                return left;
//            }
//        }
////        else if (root.val == p.val || root.val == q.val) {
////            return root;
////        }
//        TreeNode right = null;
//        if (root.val < p.val && root.val < q.val) { // p 和 q 都在右子树
//            right = lowestCommonAncestor(root.right, p, q); // 递归
//            if (right != null) {
//                return right;
//            }
//        }
////        else if (root.val == p.val || root.val == q.val) {
////            return root;
////        }
//        return root;
//    }

    /**
     * 迭代法
     * 后序遍历
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.val > p.val && temp.val > q.val) temp = temp.left;
            else if (temp.val < p.val && temp.val < q.val) temp = temp.right;
            else return temp;
        }
        return null;
    }
}
