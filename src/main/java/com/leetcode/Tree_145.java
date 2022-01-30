package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> needToTraversal = new Stack<>();
        needToTraversal.push(root);
        while (!needToTraversal.isEmpty()) {
            TreeNode visitNode = needToTraversal.pop();
            result.add(visitNode.val);

            if (visitNode.left != null) {
                needToTraversal.add(visitNode.left);
            }
            if (visitNode.right != null) {
                needToTraversal.add(visitNode.right);
            }
        }
        result = reverse(result);
        return result;
    }

    /**
     * 反转数组
     * @param result 待反转的数组
     */
    private List<Integer> reverse(List<Integer> result) {
        int times = result.size() / 2;
        for (int i = 0; i < times; i++) {
            // 待交换的元素的index
            int index = result.size() - i - 1;
            // 待交换的元素
            int temp = result.get(index);
            result.set(index, result.get(i));

            result.set(i, temp);
        }
        return result;
    }

    /**
     * Morris算法
     * @param root 待遍历的根节点
     */
    public static void morris(TreeNode root){
        //进入方法让cur = root
        TreeNode cur = root;
        //没有遍历完之前持续循环
        while (cur != null){
            /*
            对于迭代还是递归，一个节点的左子树即使为空
            它们都会让cur = left 然后回溯回来
            morris这里就会判断如果一个节点的左边为空就不会去
            */
            if (cur.left == null){
                cur = cur.right;
            } else {
                /*
                让next = cur.left，然后一直往左子树的最右面走
                （上文已经说明了为什么要这么做）
                */
                TreeNode next = cur.left;
                while (next.right != null && next.right != cur) {
                    next = next.right;
                }
                /*
                此时next经过上面while循环的
                next = next.right之后
                已经走到了左子树的最右边，我们讨论过此时要让
                next.right = cur
                以便cur走到这里时可以回溯回去，
                这件事是if(next.right == null)时
                要做的，else中做的是cur回溯回去了，
                但是下一次经历最外面的while循环
                时他还会创建一个next = cur.left的引用
                然后一直遍历到cur左子树的最右面
                这次是第二次经过这里我们知道
                next.right != null 而是next.right == cur
                为了保证原树不变我们要把这条线擦掉，
                即next.right = null，然后让cur = cur.right
                即cur的左子树走完了，让cur去走右子树。
                 */
                if (next.right == null){
                    next.right = cur;
                    cur = cur.left;
                }else {
                    next.right = null;
                    cur = cur.right;
                }
            }
        }
        /**
         * 仔细观察就会发现上图cur 所遍历的所有节点中可以分为两类
         * 第一类是有左子树的节点：A B C
         * 第二类是没有左子树的节点：D E H F G
         *
         * 对于第一类节点，cur能经过两遍，第一遍是第一次经过的时候，第二遍是回溯的时候，
         * 而对于第二类没有左子树的节点不存在往左走然后回溯的问题（递归和迭代中这类节点还是会往左走在回溯，
         * 第一次经过时处理是先序遍历，从左回溯回来第二次经过时处理是中序遍历）
         */
    }

    public List<Integer> postorderTraversal_Morris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 新建一个临时节点，用于帮助后序遍历
        TreeNode node = new TreeNode(-1);
        // 令左子树为root，右子树为空
        node.left = root;

        TreeNode cur = node;

        //没有遍历完之前持续循环
        while (cur != null){
            /*
            对于迭代还是递归，一个节点的左子树即使为空
            它们都会让cur = left 然后回溯回来
            morris这里就会判断如果一个节点的左边为空就不会去
            */
            if (cur.left == null){
                cur = cur.right;
            } else {
                /*
                让next = cur.left，然后一直往左子树的最右面走
                （上文已经说明了为什么要这么做）
                */
                TreeNode next = cur.left;
                while (next.right != null && next.right != cur) {
                    next = next.right;
                }
                if (next.right == null){
                    next.right = cur;
                    cur = cur.left;
                }else {
                    next.right = null;
                    TreeNode t = cur.left;
                    List<Integer> temp = new ArrayList<>();
                    // 将cur左子树中的所有右子节点倒查入后序遍历的结果中
                    // 后序遍历： 左 右 中
                    // cur代表“中”
                    // cur.left的内容都是在中前面
                    // 倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点。
                    while (t != null) {
                        temp.add(0, t.val);
                        t = t.right;
                    }
                    result.addAll(temp);
                    cur = cur.right;
                }
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
        TreeNode root = new TreeNode(3, root_left, root_right);

        Tree_145 tree_145 = new Tree_145();
        List<Integer> result  = tree_145.postorderTraversal(root);
//        List<Integer> result  = tree_145.postorderTraversal_Morris(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
