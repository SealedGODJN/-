package com.leetcode.Tree.FirstWeek.Traverse;

import com.leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_94 {

    /**
     * 以非递归的方式遍历
     * @param root 待遍历待树节点
     * @return 如果树节点不为空，则返回该树待中序遍历的结果<br>
     *         如果树节点为空，则返回空的数组
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> needToTraversal = new Stack<>();
        TreeNode visit = root;

        // 先沿左子树将所有的左子树的节点加入栈中
        while (visit != null) {
            needToTraversal.add(visit);
            visit = visit.left;
        }

        do {
            TreeNode visitNode = needToTraversal.pop();
            result.add(visitNode.val);

            if (visitNode.right != null) {
                needToTraversal.add(visitNode.right);
            }

        } while (!needToTraversal.isEmpty());
        return result;
    }

    /**
     * Morris算法进行中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_Morris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //进入方法让cur = root
        TreeNode cur = root;
        //没有遍历完之前持续循环
        while (cur != null){
            /*
            对于迭代还是递归，一个节点的左子树即使为空
            它们都会让cur = left 然后回溯回来
            morris这里就会判断如果一个节点的左边为空就不会去
            */
            // 第一次经过第二类节点[第二类是没有左子树的节点]
            if (cur.left == null){
                result.add(cur.val);
                cur = cur.right;

            } else {
                /*
                让next = cur.left，然后一直往左子树的最右面走
                */
                TreeNode next = cur.left;
                while (next.right != null && next.right != cur) {
                    next = next.right;
                }
                // 第一次经过第一类节点[第一类是有左子树的节点]
                if (next.right == null){
                    // 第一类节点指的是cur，而不是next
                    // result.add(next.val);
//                    result.add(cur.val);
                    next.right = cur;
                    cur = cur.left;
                }else {
                    // 第二次经过第一类节点
                    result.add(cur.val);
                    next.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }

    /**
     * 以非递归的方式遍历
     * @param root 待遍历待树节点
     * @return 如果树节点不为空，则返回该树待中序遍历的结果<br>
     *         如果树节点为空，则返回空的数组
     */
    public List<Integer> inorderTraversal_Non_Recursion_TheSame(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> record = new Stack<>();
        record.push(root);
        while (!record.isEmpty()) {
            TreeNode node = record.pop();
            if (node != null) {
                // 右
                if (node.right != null) {
                    record.push(node.right);
                }
                // 中
                record.push(node);
                // 使用空指针进行标记
                record.push(null);
                // 左
                if (node.left != null) {
                    record.push(node.left);
                }
            } else {
                node = record.pop();
                // 此处再将“中”加入到result中
                // 保证“左中右”的顺序
                result.add(node.val);
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

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_94 tree_94 = new Tree_94();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        List<Integer> result  = tree_94.inorderTraversal_Morris(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
