package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_572 {
//    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//        if (root == null && subRoot == null) {
//            return true;
//        } else if (root == null && subRoot != null) {
//            return false;
//        } else if (root != null && subRoot == null) {
//            return false;
//        }
//
//        Deque<TreeNode> level = new ArrayDeque<>();
//        level.push(root);
//        while (!level.isEmpty()) {
//            List<Integer> levelResult = new ArrayList<>();
//            int size = level.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode temp = level.pop();
//                if (temp.left != null) {
//                    level.add(temp.left);
//                }
//                if (temp.right != null) {
//                    level.add(temp.right);
//                }
////                levelResult.add(temp.val);
//                if (isSameTree(temp, subRoot)) {
//                    return true;
//                }
//            }
////            result.add(levelResult);
//        }
////        return result;
//        return false;
//    }
//
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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root,subRoot);
    }

    /**
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot != null) {
            return false;
        }
        return check(root, subRoot) || dfs(root.left, subRoot) || dfs(root.right, subRoot);
    }

    /**
     * 1、参数和返回值
     * 参数：两个树的节点
     * 返回值：boolean
     *
     * 2、终止条件
     * 叶子节点 回到上一层
     *
     * 3、单层逻辑
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root != null && subRoot == null) {
            return false;
        } else if (root == null && subRoot != null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, null, null);


        TreeNode root_right_left = new TreeNode(5);
        TreeNode root_right_right = new TreeNode(4);
        TreeNode root_right = new TreeNode(2, null, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);





        TreeNode root_left_left1 = new TreeNode(4);
        TreeNode root_left_right1 = new TreeNode(5);
        TreeNode root_left1 = new TreeNode(2, null, null);


        TreeNode root_right_left1 = new TreeNode(5);
        TreeNode root_right_right1 = new TreeNode(3);
//        TreeNode root_right1 = new TreeNode(2, root_right_left1, root_right_right1);
        TreeNode root_right1 = new TreeNode(2, null, root_right_right1);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root1 = new TreeNode(1, null, root_right1);

        Tree_572 tree_101 = new Tree_572();
        boolean result  = tree_101.isSubtree(root, root1);
        System.out.println(result);
    }
}
