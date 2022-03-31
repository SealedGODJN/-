package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.FirstWeek.Traverse.LevelTraverse.Tree_102;
import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 */
public class Tree_110 {
//    /**
//     * 借助“最大深度”，来判断每个子树的左右孩子节点的最大深度之差是否在[-1,1]之内
//     * @param root
//     * @return
//     */
//    public boolean isBalanced(TreeNode root) {
////        List<List<Integer>> result = new ArrayList<>();
//        if (root == null) {
//            return true;
//        }
//
//        Deque<TreeNode> level = new ArrayDeque<>();
//        level.push(root);
//        while (!level.isEmpty()) {
////            List<Integer> levelResult = new ArrayList<>();
////            List<TreeNode> waitToAdd = new ArrayList<>();
//            int size = level.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode temp = level.pop();
//                if (temp.left != null) {
//                    level.add(temp.left);
//                }
//                if (temp.right != null) {
//                    level.add(temp.right);
//                }
//                int balanced = getDepth(temp.left) - getDepth(temp.right);
//                if (balanced < -1 || balanced > 1) {
//                    return false;
//                }
//            }
////            level.addAll(waitToAdd);
////            result.add(levelResult);
//        }
//        return true;
//    }
//
//    // 114: 最大深度
//    public int getDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int depthLeft = getDepth(root.left);
//        int depthRigth = getDepth(root.right);
//        int depth = Math.max(depthLeft, depthRigth) + 1;
//        return depth;
//    }

    /**
     * 1、明确递归函数的参数和返回值 <br>
     * 参数：root <br>
     * 返回值：int（高度——高度只能自下而上来求解->后序遍历） <br>
     *
     * 2、明确终止条件 <br>
     *
     * 3、单层逻辑
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) == -1 ? false : true;
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getHeight(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = getHeight(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        return Math.abs(leftDepth - rightDepth) > 1 ? -1 : 1 + Math.max(leftDepth, rightDepth);
        // 高度差不在[-1, 1]中

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
        TreeNode root = new TreeNode(3, root_left, null);

        Tree_110 tree_102 = new Tree_110();
        boolean result = tree_102.isBalanced(root);
//        List<Integer> result  = tree_145.postorderTraversal_Morris(root);
        System.out.print(result + " ");

    }
}
