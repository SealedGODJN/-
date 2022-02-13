package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_111 {
    /**
     * 1、迭代写法
     *
     * 2、递归写法
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
        int result = 0;
        boolean isLeaf = false;
        if (root == null) {
            return result;
        }

        Deque<TreeNode> level = new ArrayDeque<>();
        level.push(root);
        while (!level.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
//            List<TreeNode> waitToAdd = new ArrayList<>();
            int size = level.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = level.pop();
                // 如果该节点是叶子节点，则立刻return
                if (temp.left == null && temp.right == null) {
                    return result + 1;
                }

                if (temp.left != null) {
                    level.add(temp.left);
                }
                if (temp.right != null) {
                    level.add(temp.right);
                }
                levelResult.add(temp.val);
            }
//            level.addAll(waitToAdd);
//            result.add(levelResult);
            result++;
        }
        return result;
    }

//    public int minDepth(TreeNode root) {
//        return getDepth(root);
//    }
//
//    public int getDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int depth = 1;
//        // 右子树为空
//        if (root.left != null && root.right == null) {
//            depth = getDepth(root.left) + 1;
//        }
//        // 左子树为空
//        else if (root.left == null && root.right != null) {
//            depth = getDepth(root.right) + 1;
//        }
//        // 左右子树不为空
//        else if (root.left != null && root.right != null) {
//            int depthLeft = getDepth(root.left);
//            int depthRigth = getDepth(root.right);
//            depth = Math.min(depthLeft, depthRigth) + 1;
//        }
//        return depth;
//    }

    public static void main(String[] args) {
//        TreeNode root_left_left = new TreeNode(4);
//        TreeNode root_left_right = new TreeNode(5);
//        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
        TreeNode root_left = new TreeNode(2, null, null);

        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_111 tree_111 = new Tree_111();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        int depth  = tree_111.minDepth(root);

        System.out.println(depth);
    }
}
