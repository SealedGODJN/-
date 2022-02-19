package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.FirstWeek.Traverse.Tree_94;
import com.leetcode.Tree.TreeNode;

import java.util.List;

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
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        if (root.left == null && root.right == null) {
            return sum;
        }

        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
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
