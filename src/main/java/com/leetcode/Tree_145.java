package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> needToTraversal = new Stack<>();
        needToTraversal.push(root);
        while (!needToTraversal.isEmpty()) {
            TreeNode visitNode = needToTraversal.pop();
            result.add(visitNode.val);

            if (visitNode.left != null) {
                needToTraversal.add(visitNode.left);
            }
            if (visitNode.right != null) {
                needToTraversal.add(visitNode.right);
            }
        }
        result = reverse(result);
        return result;
    }

    /**
     * 反转数组
     * @param result 待反转的数组
     */
    private List<Integer> reverse(List<Integer> result) {
        int times = result.size() / 2;
        for (int i = 0; i < times; i++) {
            // 待交换的元素的index
            int index = result.size() - i - 1;
            // 待交换的元素
            int temp = result.get(index);
            result.set(index, result.get(i));
            result.set(i, temp);
        }
        return result;
    }

    public static void main(String[] args) {
//        TreeNode root_left_left = new TreeNode(4);
//        TreeNode root_left_right = new TreeNode(5);
//        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
//
//
//        TreeNode root_right_left = new TreeNode(6);
//        TreeNode root_right_right = new TreeNode(7);
//        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

        TreeNode root_left = new TreeNode(1);
        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(3, root_left, root_right);

        Tree_145 tree_145 = new Tree_145();
        List<Integer> result  = tree_145.postorderTraversal(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
