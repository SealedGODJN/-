package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.FirstWeek.Traverse.Tree_94;
import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Tree_257 {
//    List<List<Integer>> result = new ArrayList<>();
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> allPath = new ArrayList<>();
//        if (root == null) {
//            return allPath;
//        }
//        List<Integer> path = new ArrayList<>();
//        path.add(root.val);
//
//        traversal(root, path);
//        for (List<Integer> paths : result) {
//            StringBuilder sb = new StringBuilder();
//            for (Integer integer : paths) {
//                sb.append(integer).append("->");
//            }
//            sb.delete(sb.length() - 2, sb.length());
//            allPath.add(sb.toString());
//        }
//        return allPath;
//    }
//
//    /**
//     * 递归遍历树
//     * @param root 一棵树的根节点
//     * @param path 根节点到叶子节点
//     */
//    public void traversal(TreeNode root, List<Integer> path) {
//        if (root.left == null && root.right == null) {
//            List<Integer> temp = new ArrayList<>(path);
//            result.add(temp);
//            return;
//        }
////        path.add(root.val);
//
//        if (root.left != null) {
//            path.add(root.left.val);
//            traversal(root.left, path);
//            path.remove(path.size() - 1);
//        }
//
//        if (root.right != null) {
//            path.add(root.right.val);
//            traversal(root.right, path);
//            path.remove(path.size() - 1);
//        }
//    }

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
//     * @param root
//     * @return
//     */
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> allPath = new ArrayList<>();
//        if (root == null) {
//            return allPath;
//        }
//        List<Integer> path = new ArrayList<>();
////        path.add(root.val);
//
//        traversal(root, path, allPath);
////        for (List<Integer> paths : result) {
////            StringBuilder sb = new StringBuilder();
////            for (Integer integer : paths) {
////                sb.append(integer).append("->");
////            }
////            sb.delete(sb.length() - 2, sb.length());
////            allPath.add(sb.toString());
////        }
//        return allPath;
//    }
//
//    /**
//     * 递归遍历树的每一个节点
//     * @param root
//     * @param path
//     */
//    public void traversal(TreeNode root, List<Integer> path, List<String> result) {
//        path.add(root.val);
//
//        if (root.left == null && root.right == null) {
//            StringBuilder sb = new StringBuilder();
//            for (Integer integer : path) {
//                sb.append(integer).append("->");
//            }
//            sb.delete(sb.length() - 2, sb.length());
//            result.add(sb.toString());
//        }
//
//        if (root.left != null) {
////            path.add(root.left.val);
//            traversal(root.left, path, result);
//            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
//            path.remove(path.size() - 1);
//        }
//
//        if (root.right != null) {
////            path.add(root.right.val);
//            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
//            traversal(root.right, path, result);
//            path.remove(path.size() - 1);
//        }
//
//    }

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
//     * @param root
//     * @return
//     */
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> allPath = new ArrayList<>();
//        if (root == null) {
//            return allPath;
//        }
//        String path = "";
//        traversal(root, path, allPath);
//        return allPath;
//    }
//
//    /**
//     * 递归遍历树的每一个节点
//     * @param root
//     * @param path
//     */
//    public void traversal(TreeNode root, String path, List<String> result) {
//        path += root.val;
//
//        if (root.left == null && root.right == null) {
//            result.add(path);
//            return;
//        }
//
//        if (root.left != null) {
//            traversal(root.left, path + "->", result);
//            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
//        }
//
//        if (root.right != null) {
//            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
//            traversal(root.right, path + "->", result);
//        }
//
//    }

    /**
     * 迭代法
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // 存放树节点
        Stack<TreeNode> treeStack = new Stack<>();
        Stack<String> pathStack = new Stack<>();
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        treeStack.push(root);
        pathStack.push(String.valueOf(root.val));
        while (!treeStack.isEmpty()) {
            // 取出当前路径上的节点
            TreeNode cur = treeStack.pop();
            // 取出从根到该节点的路径
            String path = pathStack.pop();
            if (cur.left == null && cur.right == null) {
                result.add(path);
                continue;
            }
            if (cur.right != null) {
                treeStack.push(cur.right);
                pathStack.push(path + "->" + String.valueOf(cur.right.val));
            }
            if (cur.left != null) {
                treeStack.push(cur.left);
                pathStack.push(path + "->" + String.valueOf(cur.left.val));

            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);

        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_257 tree_94 = new Tree_257();
        List<String> result  = tree_94.binaryTreePaths(root);
        for (String integer : result) {
            System.out.print(integer + " ");
        }
    }
}
