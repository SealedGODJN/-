package com.leetcode.Tree.SecondWeek;

import com.leetcode.TreeNode;

public class Tree_222 {
//    public int countNodes(TreeNode root) {
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
//                result++;
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
//        }
//        return result;
//    }

    public int countNodes(TreeNode root) {
        return getNodes(root);
    }

    public int getNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftNum = getNodes(root.left);
        int rigthNum = getNodes(root.right);
        int num = leftNum + rigthNum + 1;
        return num;
    }

    public static void main(String[] args) {
//        TreeNode root_left_left = new TreeNode(4);
//        TreeNode root_left_right = new TreeNode(5);
//        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
        TreeNode root_left = new TreeNode(2, null, null);

        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_222 tree_222 = new Tree_222();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        int nodeNum  = tree_222.countNodes(root);

        System.out.println(nodeNum);
    }
}
