package com.leetcode.Tree.FiveWeek;

import com.leetcode.Tree.TreeNode;

public class Tree_450 {
    /**
     * 递归法
     * 一共五种情况
     *
     * @param root
     * @param key
     * @return
     */
//    public TreeNode deleteNode(TreeNode root, int key) {
//        if (root == null) return null; // 第一种情况
//        if (root.val == key) {
//            // 第二种情况：左孩子为空，右孩子为空
//            if (root.left == null && root.right == null) {
//                return null;
//            }
//            // 第三种情况：左孩子不为空，右孩子为空
//            else if (root.left != null && root.right == null) {
//                return root.left;
//            }
//            // 第四种情况：左孩子为空，右孩子不为空
//            else if (root.left == null && root.right != null) {
//                return root.right;
//            }
//            // 第五种情况：两个孩子都不为空
//            else {
//                // 1.先找右子树的最左节点
//                TreeNode temp = root.right;
//                while (temp.left != null) {
//                    temp = temp.left;
//                }
//                temp.left = root.left;
//                return root.right;
//            }
//        }
//        if (root.val > key) root.left = deleteNode(root.left, key);
//        else if (root.val < key) root.right = deleteNode(root.right, key);
//        return root;
//    }

    /**
     * 迭代法
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.val == key) break;
            pre = cur;
            if (cur.val > key) cur = cur.left;
            else cur = cur.right;
        }
        if (pre == null) return deleteOneNode(cur); // 根节点为待删除的节点
        if (pre.val > key) {
            pre.left = deleteOneNode(cur);
        } else pre.right = deleteOneNode(cur);
        return root;
    }

    public TreeNode deleteOneNode(TreeNode cur) {
        // 第二种情况
        if (cur == null) return null;
        // 第三种情况
        if (cur.right == null) return cur.left;
        // 第四种情况
        if (cur.left == null) return cur.right;
            // 第五种情况
        else {
            TreeNode temp = cur.right;
            while (temp.left != null) {
                temp = temp.left;
            }
//            temp.left = cur; // 错误：应该把cur的左子树放到右子树的最左边
            temp.left = cur.left;
            return cur.right;
        }
    }

    /**
     * 普通二叉树的删除——递归法
     * 返回删除的节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode_BinaryTree(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.right == null) { // 第二次操作目标值
                return root.left;
            }
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            swap(root, cur); // 第一次操作目标值
        }
        root.left = deleteNode_BinaryTree(root.left, key);
        root.right = deleteNode_BinaryTree(root.right, key);
        return root;
    }

    /**
     * 交换两个节点的值
     *
     * @param value1
     * @param value2
     */
    public void swap(TreeNode value1, TreeNode value2) {
        int temp = value1.val;
        value1.val = value2.val;
        value2.val = temp;
    }
}
