package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_404 {
    /**
     * 1、确定函数的参数和返回值
     * 参数：节点
     * 返回值：累加的左叶子节点之和int
     *
     * 2、确定终止条件
     * 左右子节点都为空
     *
     * 3、确定单层逻辑
     * 先计算左右子树的sum值
     * 最后计算根节点的左叶子值
     *
     * @param root 树的根节点
     * @return 左叶子节点之和（int）
     * '
     */
//    public int sumOfLeftLeaves(TreeNode root) {
//        int sum = 0;
//        if (root == null) {
//            return sum;
//        }
//        if (root.left == null && root.right == null) {
//            return sum;
//        }
//
//        sum += sumOfLeftLeaves(root.left); // 左
//        sum += sumOfLeftLeaves(root.right); // 右
//
//        if (root.left != null && root.left.left == null && root.left.right == null) { // 中
//            sum += root.left.val;
//        }
//        return sum;
//    }

    public int sumOfLeftLeaves(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
        int sum = 0;
        Stack<TreeNode> record = new Stack<>();
        record.push(root);
        while (!record.isEmpty()) {
            TreeNode node = record.pop();
//            if (node != null) {
//                // 中
//                record.push(node);
//                // 使用空指针进行标记
//                record.push(null);

            // 中
            if (node.left != null && node.left.left == null && node.left.right == null) {
                sum += node.left.val;
            }

            // 右
            if (node.right != null) {
                record.push(node.right);

            }
            // 左
            if (node.left != null) {
                record.push(node.left);
            }
//            } else {
//                node = record.pop();
//                // 此处再将“中”加入到result中
//                // 保证“左中右”的顺序
//                result.add(node.val);
//            }
        }
//        return result;
        return sum;
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

        Tree_404 tree_94 = new Tree_404();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        int result  = tree_94.sumOfLeftLeaves(root);
        System.out.println(result);
    }

}
