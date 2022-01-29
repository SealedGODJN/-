package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_94 {

    /**
     * 以非递归的方式遍历
     * @param root 待遍历待树节点
     * @return 如果树节点不为空，则返回该树待前序遍历的结果<br></>
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
        List<Integer> result  = tree_94.inorderTraversal(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
