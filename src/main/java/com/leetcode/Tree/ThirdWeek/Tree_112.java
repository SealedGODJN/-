//package com.leetcode.Tree.ThirdWeek;
//
//import com.leetcode.Tree.SecondWeek.Tree_257_BackTracking;
//import com.leetcode.TreeNode;
//import javafx.util.Pair;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//
//public class Tree_112 {
//    boolean judge = false;
//    /**
//     * 1、明确递归函数的参数和返回值 <br>
//     * 参数：node path（记录每一条路径） result（存放结果集）
//     * 返回值：无
//     *
//     * 2、明确终止条件 <br>
//     * 遇到叶子节点
//     *
//     * 3、单次递归逻辑 <br>
//     * 1）终止条件的内部逻辑：应该把path(List<Integer>)转化为allPath中的内容
//     * 2）单次递归逻辑：对左右子树进行递归和回溯
//     *
//     * @param root 树的根节点
//     * @return 树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。<br>
//     *         如果存在，返回 true ；否则，返回 false 。
//     */
////    public boolean hasPathSum(TreeNode root, int targetSum) {
////        if (root == null) {
////            return false;
////        }
////        traversal(root, 0, targetSum);
////        return judge;
////    }
////
////    /**
////     * 递归遍历树的每一个节点，当遍历完一个路径之后，则可以退出递归遍历栈
////     * @param root 树的节点
////     * @param path 树在该路径上的所有节点val之和
////     */
////    public void traversal(TreeNode root, int path, int targetSum) {
////        path += root.val;
////
////        if (root.left == null && root.right == null) {
////            if (path == targetSum) {
////                judge = true;
////            }
////        }
////
////        if (root.left != null) {
//////            path += root.left.val;
////            traversal(root.left, path, targetSum);
////            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
//////            path -= root.left.val;
////        }
////
////        if (root.right != null) {
//////            path += root.right.val;
////            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
////            traversal(root.right, path, targetSum);
//////            path -= root.right.val;
////        }
////
////    }
////    public boolean hasPathSum(TreeNode root, int targetSum) {
////        if (root == null) {
////            return false;
////        }
////        // 叶子结点
////        if (root.left == null && root.right == null) {
////            // 且叶子结点的值==修改过后的targetSum
////            if (root.val == targetSum) {
////                return true;
////            }
////            return false;
////        }
////        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
////    }
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//        if (root == null) {
//            return false;
//        }
//
//        Stack<Pair<TreeNode, Integer>> record = new Stack<>();
//        record.push(new Pair<>(root, targetSum - root.val));
//        while (!record.isEmpty()) {
//            Pair<TreeNode, Integer> node = record.pop();
//            // 右
//            TreeNode right = node.getKey().right;
//            TreeNode left = node.getKey().left;
//
//            // 叶子结点
//            if (right == null && left == null && node.getValue() == 0) {
//                return true;
//            }
//
//            if (right != null) {
//                record.push(new Pair<>(right, node.getValue() - right.val));
//            }
//            // 左
//            if (left != null) {
//                record.push(new Pair<>(left, node.getValue() - left.val));
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
////        TreeNode root_left_left = new TreeNode(4);
////        TreeNode root_left_right = new TreeNode(5);
////        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);
//        TreeNode root_left = new TreeNode(1, null, null);
//
////        TreeNode root_right_left = new TreeNode(6);
////        TreeNode root_right_right = new TreeNode(7);
////        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);
//        TreeNode root_right = new TreeNode(1, null, null);
//
//        TreeNode root = new TreeNode(0, root_left, root_right);
//
//        Tree_112 tree_94 = new Tree_112();
//        boolean result  = tree_94.hasPathSum(root, 1);
//        System.out.println(result);
//    }
//}
