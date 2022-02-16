package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Tree_100 {
    /**
     * 迭代法
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        Deque<TreeNode> que = new ArrayDeque<>();

        // 将第一棵树的头结点加入队列
        que.push(p);
        // 将第二棵树的头结点加入队列
        que.push(q);

        // 接下来就要判断这这两个树是否相互翻转
        while (!que.isEmpty()) {
            TreeNode leftP = que.removeFirst();
            TreeNode leftQ = que.removeFirst();

            // 两个节点都为空，则继续判断
            if (leftP == null && leftQ == null) {
                continue;
            }

            // 有一个节点为空，另一个节点为空
            // 两个节点的值不一样
            // 都符合false
            if (leftP == null || leftQ == null || (leftP.val != leftQ.val)) {
                return false;
            }

//            que.add(leftP.left);
//            que.add(second.left);
//            que.add(leftP.right);
//            que.add(second.right);

            if (leftP.left == null && leftQ.left != null) {
                return false;
            } else if (leftP.left != null && leftQ.left == null) {
                return false;
            } else if (leftP.left != null && leftQ.left != null) {
                que.add(leftP.left);
                que.add(leftQ.left);
            }

            if (leftP.right == null && leftQ.right != null) {
                return false;
            } else if (leftP.right != null && leftQ.right == null) {
                return false;
            } else if (leftP.right != null && leftQ.right != null) {
                que.add(leftP.right);
                que.add(leftQ.right);
            }
        }
        return true;
    }

//    /**
//     * 使用递归
//     *
//     * 1、确定递归的参数和返回值
//     * 参数：两个树的根节点
//     * 返回值：boolean（根节点和左右子树是否相同）
//     * 2、确定中止条件
//     * 遇到叶子节点，则return
//     *
//     * 3、确定单层逻辑
//     * 比较两个树的值和左右子树的值
//     *
//     * @param p
//     * @param q
//     * @return
//     */
//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        return compare(p, q);
//    }
//
//    /**
//     *
//     * @param left 第一棵树的一个节点
//     * @param right 第二课树的一个节点
//     * @return
//     */
//    private boolean compare(TreeNode left, TreeNode right) {
//        if (left == null && right == null) {
//            return true;
//        }
//        else if (left == null && right != null) {
//            return false;
//        } else if (left != null && right == null) {
//            return false;
//        } else if (left.val != right.val) return false;
//        boolean result1 = compare(left.left, right.left);
//        boolean result2 = compare(left.right, right.right);
//        return result1 && result2;
//    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, null, null);


        TreeNode root_right_left = new TreeNode(5);
        TreeNode root_right_right = new TreeNode(4);
        TreeNode root_right = new TreeNode(2, null, null);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, null, root_right);

        TreeNode root_left_left1 = new TreeNode(4);
        TreeNode root_left_right1 = new TreeNode(5);
        TreeNode root_left1 = new TreeNode(2, null, null);


        TreeNode root_right_left1 = new TreeNode(5);
        TreeNode root_right_right1 = new TreeNode(3);
//        TreeNode root_right1 = new TreeNode(2, root_right_left1, root_right_right1);
        TreeNode root_right1 = new TreeNode(2, null, null);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root1 = new TreeNode(1, null, root_right1);

        Tree_100 tree_101 = new Tree_100();
        boolean result  = tree_101.isSameTree(root, root1);
        System.out.println(result);
    }
}
