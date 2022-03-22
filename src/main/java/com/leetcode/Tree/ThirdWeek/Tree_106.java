package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.TreeNode;


import java.util.Arrays;

public class Tree_106 {

    /**
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * <p>
     * <p>
     * 106.从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意: 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * <p>
     * 后序遍历 postorder = [9,15,7,20,3]
     * <p>
     * 返回如下的二叉树
     *
     * @param inorder   前序遍历结果
     * @param postorder 后序遍历结果
     * @return 生成的树的根节点
     */
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        // 数组长度为0，则树为空
//        if (inorder.length == 0 && postorder.length == 0) {
//            return null;
//        }
//        if (postorder.length == 1) {
//            return new TreeNode(postorder[0], null, null);
//        }
//        int rootVal = postorder[postorder.length - 1];
//
//        int rootIndex = -1;
//        for (int i = 0; i < inorder.length; i++) {
//            if (inorder[i] == rootVal) {
//                rootIndex = i;
//                break;
//            }
//        }
//
//        int[] inorderLeft = new int[0];
//        int[] postorderLeft;
//        TreeNode left;
//        if (0 <= rootIndex && 0 <= inorder.length) {
//            inorderLeft = Arrays.copyOfRange(inorder, 0, rootIndex);
//            postorderLeft = Arrays.copyOfRange(postorder, 0, inorderLeft.length);
//            left = buildTree(inorderLeft, postorderLeft);
//        } else {
//            left = null;
//        }
//
//        TreeNode right;
//        if (rootIndex + 1 < inorder.length && inorderLeft.length + 1 <= postorder.length - 1) {
//            int[] inorderRight = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
//            int[] postorderRight = Arrays.copyOfRange(postorder, inorderLeft.length, postorder.length - 1);
//            right = buildTree(inorderRight, postorderRight);
//        } else {
//            right = null;
//        }
//
//        return new TreeNode(rootVal, left, right);
//    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return traverse(inorder, 0, inorder.length, postorder, 0, inorder.length);
    }

    public TreeNode traverse(int[] inorder, int inorderLeft, int inorderRight, int[] postorder, int postOrderLeft, int postOrderRight) {
        // 数组长度为0，则树为空
        if (postOrderLeft >= postOrderRight) {
            return null;
        }
        if (postOrderLeft + 1 == postOrderRight) {
            return new TreeNode(postorder[postOrderLeft], null, null);
        }
        int rootVal = postorder[postOrderRight - 1];

        int rootIndex = inorderLeft;
        for (int i = rootIndex + 1; i < inorderRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        // 左中序区间，左闭右开[leftInorderBegin, leftInorderEnd)
        int leftInorderBegin = inorderLeft;
        int leftInorderEnd = rootIndex;
        // 左后序区间，左闭右开[leftPostorderBegin, leftPostorderEnd)
        int leftPostorderBegin = postOrderLeft;
        // rootIndex - inorderLeft为左子树节点的数量
        int leftPostorderEnd = postOrderLeft + rootIndex - inorderLeft;
        // 终止位置是 需要加上 中序区间的大小size
        TreeNode leftNode = traverse(inorder, leftInorderBegin, leftInorderEnd, postorder, leftPostorderBegin, leftPostorderEnd);


        // 右中序区间，左闭右开[rightInorderBegin, rightInorderEnd)
        int rightInorderBegin = leftInorderEnd + 1;
        int rightInorderEnd = inorderRight;
        // 总节点的数量 - 左节点的数量 = 右节点的数量
        int rightPostorderBegin = postOrderLeft + leftInorderEnd - leftInorderBegin;
        // 去除最后一个节点（后序遍历的最后一个节点为根节点）
        int rightPostorderEnd = postOrderRight - 1;

        // 右中序区间，左闭右开[rightInorderBegin, rightInorderEnd)
        TreeNode rightNode = traverse(inorder, rightInorderBegin, rightInorderEnd, postorder, rightPostorderBegin, rightPostorderEnd);

        return new TreeNode(rootVal, leftNode, rightNode);
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7}, postorder = {9, 15, 7, 20, 3};
//        int[] inorder = {2,1}, postorder = {1,2};

        Tree_106 tree_106 = new Tree_106();
        TreeNode temp = tree_106.buildTree(inorder, postorder);
        System.out.println(temp);
    }
}
