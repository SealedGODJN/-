package com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse;

import com.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
public class Tree_637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> level = new ArrayDeque<>();
        level.push(root);
        while (!level.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
            List<TreeNode> waitToAdd = new ArrayList<>();
            while (!level.isEmpty()) {
                TreeNode temp = level.pop();
                if (temp.left != null) {
                    waitToAdd.add(temp.left);
                }
                if (temp.right != null) {
                    waitToAdd.add(temp.right);
                }
                levelResult.add(temp.val);
            }
            level.addAll(waitToAdd);
//            result.add(levelResult);
            double sum = 0;
            for (Integer integer : levelResult) {
                sum += integer;
            }
            double size = levelResult.size();
            sum = sum / size;
            result.add(sum);
        }
        return result;
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

        Tree_637 tree_637 = new Tree_637();
        List<Double> result  = tree_637.averageOfLevels(root);
//        List<Integer> result  = tree_145.postorderTraversal_Morris(root);
        for (Double d : result) {
            System.out.print(d + " ");
        }
        System.out.println();
    }
}
