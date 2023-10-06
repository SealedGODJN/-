package com.leetcode.Tree.ThirdWeek;

import com.leetcode.TreeNode;

import java.util.Arrays;

public class Tree_105 {

    /**
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     *
     * @param inorder
     * @param preorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 数组长度为0，则树为空
        if (inorder.length == 0 && preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0], null, null);
        }
        int rootVal = preorder[0];

        int rootIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        int[] inorderLeft = new int[0];
        int[] preorderLeft;
        TreeNode left;
//        if (0 <= rootIndex && 0 <= inorder.length) {
            inorderLeft = Arrays.copyOfRange(inorder, 0, rootIndex);
            preorderLeft = Arrays.copyOfRange(preorder, 1, inorderLeft.length + 1);
            left = buildTree(preorderLeft, inorderLeft);
//        } else {
//            left = null;
//        }

        TreeNode right;
//        if (rootIndex + 1 < inorder.length && inorderLeft.length + 1 <= preorder.length) {
            int[] inorderRight = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
            int[] preorderRight = Arrays.copyOfRange(preorder, inorderLeft.length + 1, preorder.length);
            right = buildTree(preorderRight, inorderRight);
//        } else {
//            right = null;
//        }

        return new TreeNode(rootVal, left, right);
    }

    public static void main(String[] args) {
//        int[] inorder = {9,3,15,20,7}, preorder = {3,9,20,15,7};
        int[] inorder = {2,1}, preorder = {1,2};

        Tree_105 tree_106 = new Tree_105();
        TreeNode temp = tree_106.buildTree(preorder, inorder);
        System.out.println(temp);
    }
}
