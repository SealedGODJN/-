package com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_199 {
    /**
     * 利用层序遍历的结果得到树的右视图，即取树层序遍历每一层的最后一个元素
     * @param root 待遍历的节点
     * @return 如果root为空，返回空的数组<br>
     *         如果root不为空，返回树的右视图
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
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
            result.add(levelResult.get(levelResult.size() - 1));
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

        Tree_199 tree_199 = new Tree_199();
        List<Integer> result  = tree_199.rightSideView(root);
//        List<Integer> result  = tree_145.postorderTraversal_Morris(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}
