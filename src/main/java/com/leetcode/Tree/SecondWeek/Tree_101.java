package com.leetcode.Tree.SecondWeek;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Tree_101 {
    /**
     * 第一种方法：<br>
     * 1、层序遍历，每一层得到的序列都是一个回文串，则使用栈可以解决 <br>
     *
     * 第二种方法：<br>
     * 1、后序遍历<br>
     * 2、左子树的遍历顺序：<左><右><中> <br>
     * 3、右子树的遍历顺序：<右><左><中> <br>
     * <br>
     *
     * 递归实现：
     * 1、确定递归函数的参数和返回值？ <br>
     * boolean traversal(TreeNode left, TreeNode right) <br>
     * <br>
     * 2、确定终止条件？
     * 左节点和右节点都为空，有如下几种情况： <br>
     * 1）左空、右空 return true <br>
     * 2）左不空、右空 return false <br>
     * 3）左空、右不空 return false <br>
     * 4)左右节点都不为空，则比较节点数值，相同->true <br>
     *                               不 ->false
     *                               <br>
     * 3、确定单层递归的逻辑
     * 如果左右节点的数值相同，则继续向下比较。<br>
     * 向下比较左->左and右->右 <br>
     *        左->右and右->左
     *
     *
     * @param root
     * @return
     */
//    public boolean isSymmetric(TreeNode root) {
//        if (root.left == null && root.right == null) {
//            return true;
//        }
//        return compare(root.left, root.right);
//    }
//
//    private boolean compare(TreeNode left, TreeNode right) {
//        if (left == null && right == null) {
//            return true;
//        }
//        else if (left == null && right != null) {
//            return false;
//        } else if (left != null && right == null) {
//            return false;
//        } else if (left.val != right.val) return false;
//        boolean result1 = compare(left.left, right.right);
//        boolean result2 = compare(left.right, right.left);
//        return result1 && result2;
//    }

    /**
     * 迭代法<br>
     * 和递归法类似
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> que = new ArrayDeque<>();

        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null && root.right != null) {
            return false;
        } else if (root.left != null && root.right == null) {
            return false;
        } else if (root.left != null && root.right != null) {
            // 将左子树头结点加入队列
            que.push(root.left);
            // 将右子树头结点加入队列
            que.push(root.right);
        }
        // 接下来就要判断这这两个树是否相互翻转
        while (!que.isEmpty()) {
            TreeNode left = que.removeFirst();
            TreeNode right = que.removeFirst();
            if (left.val != right.val) {
                return false;
            }

            if (left.left == null && right.right != null) {
                return false;
            } else if (left.left != null && right.right == null) {
                return false;
            } else if (left.left != null && right.right != null) {
                que.add(left.left);
                que.add(right.right);
            }

            if (left.right == null && right.left != null) {
                return false;
            } else if (left.right != null && right.left == null) {
                return false;
            } else if (left.right != null && right.left != null) {
                que.add(left.right);
                que.add(right.left);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(4);
        TreeNode root_left_right = new TreeNode(5);
        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);


        TreeNode root_right_left = new TreeNode(5);
        TreeNode root_right_right = new TreeNode(4);
        TreeNode root_right = new TreeNode(2, root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_101 tree_101 = new Tree_101();
        boolean result  = tree_101.isSymmetric(root);
        System.out.println(result);
    }
}
