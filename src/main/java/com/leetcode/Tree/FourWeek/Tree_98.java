package com.leetcode.Tree.FourWeek;

import com.leetcode.TreeNode;

import java.util.Stack;

public class Tree_98 {
//    List<Integer> result = new ArrayList<>();
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        traverse(root);
//        for(int i = 0; i < result.size() - 1; i++) {
//            if (result.get(i) >= result.get(i + 1)) return false;
//        }
//        return true;
//    }
//
//    public void traverse(TreeNode root) {
//        if (root.left != null) {
//            traverse(root.left);
//        }
//        result.add(root.val);
//        if (root.right != null) {
//            traverse(root.right);
//        }
//    }

//    /**
//     * 不是单纯的比较左子节点小于中间节点，右子节点大于中间节点
//     * 而是：
//     * 要比较的是 左子树所有节点小于中间节点，右子树所有节点大于中间节点
//     *
//     * 1、确定递归函数，返回值以及参数
//     * 参数：节点node 和 中间节点的值val
//     *
//     * 2、确定终止条件
//     * 空节点 return true
//     *
//     * 3、确定单层递归的逻辑
//     * 中序遍历，发现有节点大于maxVal
//     * return false
//     *
//     * @param root
//     * @return
//     */
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        boolean left = isValidBST(root.left);
//        if (pre != null && pre.val >= root.val) {
//            return false;
//        }
//        pre = root;
//
//        boolean right = isValidBST(root.right);
//
//        return left & right;
//    }
//    TreeNode pre = null; // 用来记录前一个节点

    /**
     * 基于中序遍历
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> record = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null || !record.isEmpty()) {
            if (cur != null) {
                record.push(cur);
                cur = cur.left; // 左
            } else {
                cur = record.pop(); // 中
                if (pre != null && pre.val >= cur.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right; // 右
            }
        }
        return true;
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
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_98 tree_94 = new Tree_98();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        boolean result = tree_94.isValidBST(root);
        System.out.print(result);
    }
}
