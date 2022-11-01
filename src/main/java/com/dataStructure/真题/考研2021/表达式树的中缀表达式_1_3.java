package com.dataStructure.真题.考研2021;

import com.dataStructure.tree.TreeNode;

public class 表达式树的中缀表达式_1_3 {
    public static void BtreeToExpress(TreeNode root) {
        BtreeToExp(root, 1);
    }

    public static void BtreeToExp(TreeNode root, int deep) {
        if (root == null) return;
        else if (root.left == null && root.right == null) {
            System.out.print(root.val);
        } else {
            if (deep > 1)
                System.out.print("(");

            BtreeToExp(root.left, deep + 1);
            System.out.print(root.val);
            BtreeToExp(root.right, deep + 1);

            if (deep > 1)
                System.out.print(")");
        }
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode("4");
        TreeNode root_left_right = new TreeNode("5");
        TreeNode root_left = new TreeNode("+", root_left_left, root_left_right);


        TreeNode root_right_left = new TreeNode("6");
        TreeNode root_right_right = new TreeNode("7");
        TreeNode root_right = new TreeNode("*", root_right_left, root_right_right);

        //
//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode("+", root_left, root_right);

//        BtreeToExpress(root);
        BtreeToExp(root, 1);
    }
}
