package com.leetcode.Tree.ThirdWeek;

import com.leetcode.Tree.TreeNode;

import java.util.Arrays;

/**
 * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
 *
 * 创建一个根节点，其值为nums 中的最大值。
 * 递归地在最大值 左边 的子数组前缀上构建左子树。
 * 递归地在最大值 右边 的子数组后缀上构建右子树。
 * 返回nums 构建的 最大二叉树 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Tree_654 {

    /**
     * 采用递归的方式处理nums数组
     *
     * 1、找到数组中的最大值
     * 2、划分左子树（左子树，递归调用）
     * 3、划分
     *
     * 1、确定递归的返回值和参数
     * 返回值：TreeNode
     * 参数：数组
     *
     * 2、确定终止条件
     * nums.length == 1 直接返回TreeNode(nums[0], null, null)
     *
     * 3、确定单层逻辑
     * 1）找到数组最大值
     * 2）划分左子树
     * 3）划分右子树
     *
     * @param nums 基于nums生成树
     * @return 树的根节点
     */
//    public TreeNode constructMaximumBinaryTree(int[] nums) {
//        if (nums.length == 0) {
//            return null;
//        }
//        if (nums.length == 1) {
//            return new TreeNode(nums[0], null, null);
//        }
//
//        // 找出数组中最大值所在的index
//        int maxIndex = 0;
//        for (int i = maxIndex + 1; i < nums.length; i++) {
//            if (nums[maxIndex] < nums[i]) {
//                maxIndex = i;
//            }
//        }
//        int[] numsLeft = Arrays.copyOfRange(nums, 0, maxIndex);
//        int[] numsRight = Arrays.copyOfRange(nums, maxIndex + 1, nums.length);
//        TreeNode left = constructMaximumBinaryTree(numsLeft);
//        TreeNode right = constructMaximumBinaryTree(numsRight);
//
//        return new TreeNode(nums[maxIndex], left, right);
//    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return traversal(nums, 0, nums.length);
    }

    public TreeNode traversal(int[] nums, int left, int right) {
//        if (nums.length == 0) {
        // 区间长度为0
        if (left >= right) {
            return null;
        }
        // 区间长度为1
        if (left == right - 1) {
            return new TreeNode(nums[left], null, null);
        }

        // 找出数组中最大值所在的index
        int maxIndex = left;
        for (int i = maxIndex + 1; i < right; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
//        int[] numsLeft = Arrays.copyOfRange(nums, 0, maxIndex);
//        int[] numsRight = Arrays.copyOfRange(nums, maxIndex + 1, nums.length);

        // 左闭右开：[left, maxValueIndex)
        TreeNode leftNode = traversal(nums, left, maxIndex);
        // 左闭右开：[maxValueIndex + 1, right)
        TreeNode rightNode = traversal(nums, maxIndex + 1, right);

        return new TreeNode(nums[maxIndex], leftNode, rightNode);
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,6,0,5};
        Tree_654 tree_654 = new Tree_654();
        TreeNode root = tree_654.constructMaximumBinaryTree(nums);
        System.out.println(root);
    }
}
