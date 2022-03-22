package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse.Tree_102;
import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_513 {
    /**
     * 【基于层序遍历】
     * 513.找树左下角的值
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     *
     * @param root 待遍历的节点
     * @return 树的最后一行找到最左边的值
     */
    public int findBottomLeftValue(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
        int result = 0;

        Deque<TreeNode> level = new ArrayDeque<>();
        level.push(root);
        while (!level.isEmpty()) {
//            List<Integer> levelResult = new ArrayList<>();
//            List<TreeNode> waitToAdd = new ArrayList<>();
            int size = level.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = level.pop();

                // 获取最后一行的第一个节点
                if (i == 0) {
                    result = temp.val;
                }
                if (temp.left != null) {
                    level.add(temp.left);
                }
                if (temp.right != null) {
                    level.add(temp.right);
                }
//                levelResult.add(temp.val);
            }
//            level.addAll(waitToAdd);
//            result.add(levelResult);
        }
//        return result.get(result.size() - 1).get(0);
        return result;
    }

//    public int maxDepth = 0;
////    public int leftDepth = 0;
//    public int maxLeftValue = 0;

//    /**
//     * 根据最大深度，确定树的最后一层
//     *
//     * 基于前序遍历，优先搜索左子树的左节点
//     *
//     * 1.确定递归函数的参数和返回值
//     * 参数：TreeNode节点 int深度
//     * 返回值：void
//     * 两个全局变量：maxDepth 最大深度 maxLeftValue 最大深度的左叶子节点值
//     *
//     * 2.确定递归的终止条件
//     * 1）深度为最大深度，且深度不再变化
//     * 2）找到了最左节点的值
//     *
//     * 3、确定单层逻辑
//     * 前序遍历【优先遍历左子树的左节点】
//     *
//     * @param root
//     * @return
//     */
//    public int findBottomLeftValue(TreeNode root) {
//        if (root.left == null && root.right == null) {
//            return root.val;
//        }
//        find(root, 0);
//        return maxLeftValue;
//    }
//
//    /**
//     * 为什么不需要返回值呢？
//     * 为什么不把深度作为返回值？
//     * 因为本题主要目的是去寻找最深一层的节点
//     *
//     * @param root
//     * @param depth
//     */
//    public void find(TreeNode root, int depth) {
//        if (root.left == null && root.right == null) {
//            if (depth > maxDepth) {
//                maxDepth = depth;
//                maxLeftValue = root.val;
//                return;
//            }
//        }
//
//        if (root.left != null) {
//            find(root.left, depth + 1);
//        }
//        if (root.right != null) {
//            find(root.right, depth + 1);
//        }
////        if (depth >= maxDepth) {
////            maxDepth = depth;
////        }
//    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(7);
        TreeNode root_left = new TreeNode(2, root_left_left, null);


        TreeNode root_right_left = new TreeNode(5, root_left_right, null);
        TreeNode root_right_right = new TreeNode(6);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_513 tree_102 = new Tree_513();
        int result  = tree_102.findBottomLeftValue(root);
        System.out.println(result);
    }
}
