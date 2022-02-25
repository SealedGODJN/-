package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse.Tree_102;
import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_513 {
    /**
     * 513.找树左下角的值
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     *
     * @param root 待遍历的节点
     * @return 树的最后一行找到最左边的值
     */
    public int findBottomLeftValue(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Deque<TreeNode> level = new ArrayDeque<>();
        level.push(root);
        while (!level.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
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
                levelResult.add(temp.val);
            }
//            level.addAll(waitToAdd);
            result.add(levelResult);
        }
        return result.get(result.size() - 1).get(0);
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

        Tree_513 tree_102 = new Tree_513();
        int result  = tree_102.findBottomLeftValue(root);
        System.out.println(result);
    }
}
