package com.leetcode.Tree.遍历二叉树;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;

public class Tree_104 {
//    /**
//     *
//     * @param root
//     * @return
//     */
//    public int maxDepth(TreeNode root) {
//        return traverse(root, 1);
//    }
//
//    /**
//     * 1、方法参数+返回值
//     * 返回当前层的depth
//     *
//     * 2、终止条件
//     * 当前节点为空，返回0
//     *
//     * 3、单层逻辑
//     * 比较左右子树的深度谁更深
//     *
//     * @param root
//     */
//    public int traverse(TreeNode root, int depth) {
//        if (root != null) {
//            int left = Math.max(depth, traverse(root.left, depth + 1));
//            int right = Math.max(depth, traverse(root.right, depth + 1));
//            return left > right ? left : right;
//        }
//        return 0;
//    }

    int maxDepth = 0;

    int depth = 0;

    public int maxDepth(TreeNode root) {
        traverse(root);
        return maxDepth;
    }

    public void traverse(TreeNode root) {
        if (root != null) {
            // 要放在返回之前
            depth++;
            if (root.left == null && root.right == null) {
                maxDepth = Math.max(maxDepth, depth);
            }
//            depth++;
            // 回溯解法
            traverse(root.left);
            traverse(root.right);
            depth--;
        }
    }


    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);

        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_104 tree104 = new Tree_104();
        System.out.println(tree104.maxDepth(root));
    }
}
