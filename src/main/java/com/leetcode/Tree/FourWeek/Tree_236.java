package com.leetcode.Tree.FourWeek;

import com.leetcode.Tree.TreeNode;
import sun.reflect.generics.tree.Tree;

public class Tree_236 {
    /**
     * 即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
     *
     * @param root 根节点
     * @param p    其中一个树节点
     * @param q    其中一个树节点
     * @return p和q的公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
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

        Tree_236 tree_94 = new Tree_236();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        TreeNode result = tree_94.lowestCommonAncestor(root, root_left, root_right_left);
        if (result != null) {
            System.out.print(result.val);
        }
    }
}
