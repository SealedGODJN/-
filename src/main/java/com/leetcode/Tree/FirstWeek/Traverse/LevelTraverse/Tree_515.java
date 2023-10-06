package com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse;

import com.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_515 {
    /**
     * 输出每一行的最大值
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> level = new ArrayDeque<>();
        level.push(root);
        while (!level.isEmpty()) {
            int levelResult = Integer.MIN_VALUE;
//            List<TreeNode> waitToAdd = new ArrayList<>();
            int size = level.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = level.pop();
                if (temp.left != null) {
                    level.add(temp.left);
                }
                if (temp.right != null) {
                    level.add(temp.right);
                }
                if (levelResult < temp.val) {
                    levelResult = temp.val;
                }
            }
//            level.addAll(waitToAdd);
            result.add(levelResult);
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


        Tree_515 tree_515 = new Tree_515();
        List<Integer> result  = tree_515.largestValues(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
