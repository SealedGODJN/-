package com.leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_104 {
    /**
     * 递归实现：
     * 1、确定递归函数的参数和返回值？ <br>
     * int getDepth(TreeNode root)<br>
     * <br>
     *
     *
     * 2、确定终止条件？<br>
     * 如果树的节点为空，则高度为0 <br>
     * if (root == NULL) return 0; <br>
     *
     * 3、确定单层递归的逻辑 <br>
     * 1）递归root的左子树 depthLeft = getDepth(root.left); <br>
     * 2）递归root的右子树 depthRight = getDepth(root.right); <br>
     * 3）返回最大深度 return max(depthLeft, depthRight) + 1; <br>
     * （后序遍历，按照左右中的顺序） <br>
     *
     *
     */
//    public int maxDepth(TreeNode root) {
////        List<List<Integer>> result = new ArrayList<>();
//        int result = 0;
//        if (root == null) {
//            return result;
//        }
//
//        Deque<TreeNode> level = new ArrayDeque<>();
//        level.push(root);
//        while (!level.isEmpty()) {
//            List<Integer> levelResult = new ArrayList<>();
////            List<TreeNode> waitToAdd = new ArrayList<>();
//            int size = level.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode temp = level.pop();
//                if (temp.left != null) {
//                    level.add(temp.left);
//                }
//                if (temp.right != null) {
//                    level.add(temp.right);
//                }
//                levelResult.add(temp.val);
//            }
////            level.addAll(waitToAdd);
////            result.add(levelResult);
//            result++;
//        }
//        return result;
//    }

    public int maxDepth(TreeNode root) {
        return getDepth(root);
    }

    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthLeft = getDepth(root.left);
        int depthRigth = getDepth(root.right);
        int depth = Math.max(depthLeft, depthRigth) + 1;
        return depth;
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

        Tree_104 tree_104 = new Tree_104();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        int depth  = tree_104.maxDepth(root);

        System.out.println(depth);
    }
}
