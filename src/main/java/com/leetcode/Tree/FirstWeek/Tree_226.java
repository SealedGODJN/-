package com.leetcode.Tree.FirstWeek;

import com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse.Tree_102;
import com.leetcode.TreeNode;

import java.util.List;

public class Tree_226 {
    /**
     * 基于层序遍历的二叉树的翻转
     * @param root
     * @return
     */
//    public TreeNode invertTree(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (root == null) {
//            return root;
//        }
//
//        Deque<TreeNode> level = new ArrayDeque<>();
//        level.push(root);
//        while (!level.isEmpty()) {
//            List<Integer> levelResult = new ArrayList<>();
//            List<TreeNode> waitToAdd = new ArrayList<>();
//            while (!level.isEmpty()) {
//                TreeNode temp = level.pop();
//                if (temp.left != null) {
//                    waitToAdd.add(temp.left);
//                }
//                if (temp.right != null) {
//                    waitToAdd.add(temp.right);
//                }
////                levelResult.add(temp.val);
//                TreeNode swap = temp.right;
//                temp.right = temp.left;
//                temp.left = swap;
//            }
//            level.addAll(waitToAdd);
//            result.add(levelResult);
//        }
//        return root;
//    }

    /**
     * 基于中序遍历的二叉树的翻转
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
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
        TreeNode root = new TreeNode(3, root_left, root_right);

        Tree_226 tree_226 = new Tree_226();
        tree_226.invertTree(root);
        Tree_102 tree_102 = new Tree_102();
        List<List<Integer>> result  = tree_102.levelOrder(root);
//        List<Integer> result  = tree_145.postorderTraversal_Morris(root);
        for (List<Integer> level : result) {
            for (Integer integer : level) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
