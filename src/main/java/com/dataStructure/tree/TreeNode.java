package com.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public String val;
    public int height;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        this.val = "";
        this.left = null;
        this.right = null;
    }

    public TreeNode(String val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(String val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
//
//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<Integer>();
//        preorder(root, result);
//        return result;
//    }
//
//    public void preorder(TreeNode root, List<Integer> result) {
//        if (root == null) {
//            return;
//        }
//        result.add(root.val);
//        preorder(root.left, result);
//        preorder(root.right, result);
//    }
//
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        inorder(root, res);
//        return res;
//    }
//
//    void inorder(TreeNode root, List<Integer> list) {
//        if (root == null) {
//            return;
//        }
//        inorder(root.left, list);
//        list.add(root.val);             // 注意这一句
//        inorder(root.right, list);
//    }
//
//    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        postorder(root, res);
//        return res;
//    }
//
//    void postorder(TreeNode root, List<Integer> list) {
//        if (root == null) {
//            return;
//        }
//        postorder(root.left, list);
//        postorder(root.right, list);
//        list.add(root.val);             // 注意这一句
//    }
}