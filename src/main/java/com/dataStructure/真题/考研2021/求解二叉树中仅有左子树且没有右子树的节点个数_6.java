//package com.dataStructure.真题.考研2021;
//
//import com.dataStructure.tree.TreeNode;
//
//public class 求解二叉树中仅有左子树且没有右子树的节点个数_6 {
//
//    int num = 0;
//
//    public static void onlyLeft(TreeNode root){
//        if (root != null) {
//            if(root.left != null && root.right == null){
//                System.out.println(root);
//            }
//            onlyLeft(root.left);
//            onlyLeft(root.right);
//        }
//    }
//
//    public static void main(String[] args) {
//        TreeNode root_left_left = new TreeNode(4);
//        TreeNode root_left_right = new TreeNode(5);
//        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
//
//
//        TreeNode root_right_left = new TreeNode(6);
//        TreeNode root_right_right = new TreeNode(7);
//        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);
////
////        TreeNode root_left = new TreeNode(1);
////        TreeNode root_right = new TreeNode(2);
//        TreeNode root = new TreeNode(3, root_left, root_right);
//    }
//}
