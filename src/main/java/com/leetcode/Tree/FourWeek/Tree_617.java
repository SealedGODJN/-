package com.leetcode.Tree.FourWeek;

import com.leetcode.TreeNode;

public class Tree_617 {

    /**
     * 递归法，参考Tree_100：相同的树
     *
     * @param root1 第一棵树
     * @param root2 第二棵树
     * @return 合并后的树
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 首先排除空节点的情况
        if (root1 == null && root2 != null) {
            return root2;
        } else if (root1 != null && root2 == null) {
            return root1;
        } else if (root1 == null && root2 == null) return null;

        // 两棵树不为空
        root1.val += root2.val;

        // 此时就是：左右节点都不为空
        // 此时才做递归，做下一层的判断
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

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

        Tree_617 tree_101 = new Tree_617();
        TreeNode result = tree_101.mergeTrees(root, root1);
        System.out.println(result);
    }
}
