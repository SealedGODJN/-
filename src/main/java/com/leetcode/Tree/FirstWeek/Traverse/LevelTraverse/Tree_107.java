package com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
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
            result.add(levelResult);
        }
        List<List<Integer>> resultReverse = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            resultReverse.add(result.get(i));
        }
        return resultReverse;
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

        Tree_107 tree_107 = new Tree_107();
        List<List<Integer>> result  = tree_107.levelOrderBottom(root);
//        List<Integer> result  = tree_145.postorderTraversal_Morris(root);
        for (List<Integer> level : result) {
            for (Integer integer : level) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
