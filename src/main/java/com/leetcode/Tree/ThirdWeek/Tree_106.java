package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.TreeNode;


import java.util.Arrays;
import java.util.List;

public class Tree_106 {

    /**
     *
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     *
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 数组长度为0，则树为空
        if (inorder.length == 0 && postorder.length == 0) {
            return null;
        }
        if (postorder.length == 1) {
            return new TreeNode(postorder[0], null, null);
        }
        int rootVal = postorder[postorder.length - 1];

        int rootIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        int[] inorderLeft = new int[0];
        int[] postorderLeft;
        TreeNode left;
        if (0 <= rootIndex && 0 <= inorder.length) {
            inorderLeft = Arrays.copyOfRange(inorder, 0, rootIndex);
            postorderLeft = Arrays.copyOfRange(postorder, 0, inorderLeft.length);
            left = buildTree(inorderLeft, postorderLeft);
        } else {
            left = null;
        }

        TreeNode right;
        if (rootIndex + 1 < inorder.length && inorderLeft.length + 1 <= postorder.length - 1) {
            int[] inorderRight = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
            int[] postorderRight = Arrays.copyOfRange(postorder, inorderLeft.length, postorder.length - 1);
            right = buildTree(inorderRight, postorderRight);
        } else {
            right = null;
        }

        return new TreeNode(rootVal, left, right);
    }

    public static void main(String[] args) {
//        int[] inorder = {9,3,15,20,7}, postorder = {9,15,7,20,3};
        int[] inorder = {2,1}, postorder = {1,2};

        Tree_106 tree_106 = new Tree_106();
        TreeNode temp = tree_106.buildTree(inorder, postorder);
        System.out.println(temp);
    }
}
