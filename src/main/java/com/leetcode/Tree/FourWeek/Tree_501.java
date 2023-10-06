package com.leetcode.Tree.FourWeek;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Tree_501 {
//    /**
//     * 迭代法
//     * @param root
//     * @return
//     */
//    public int[] findMode(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        int maxCount = 1;
//        int count = 1;
//
//        Stack<TreeNode> record = new Stack<>();
//        TreeNode pre = null;
//        TreeNode cur = root;
//        while (cur != null || !record.isEmpty()) {
//            if (cur != null) {
//                record.push(cur);
//                cur = cur.left; // 左
//            } else {
//                cur = record.pop(); // 中
//                if (pre == null) {
//                    count = 1;
//                } else if (pre.val == cur.val) {
//                    count++;
//                } else if (pre.val != cur.val) {
//                    count = 1;
//                }
//
//                pre = cur;
//
//                // TODO:需要分开处理maxCount
//                if (count == maxCount) { // 如果和最大值相同，放进result中
//                    result.add(cur.val);
//                }
//
//                if (count > maxCount) { // 如果计数大于最大值频率
//                    maxCount = count;
//                    result.clear();
//                    result.add(cur.val);
//                }
//
//
//                cur = cur.right; // 右
//            }
//        }
//        int[] res = new int[result.size()];
//        for (int i = 0; i < res.length; i++) {
//            res[i] = result.get(i);
//        }
//        return res;
//    }

    /**
     * 递归法
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        searchBST(root);
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    int maxCount; // 最大频率
    int count; // 统计频率
    TreeNode pre = null;
    List<Integer> result = new ArrayList<>();

    public void searchBST(TreeNode cur) {
        if (cur == null) {
            return;
        }

        searchBST(cur.left);

        if (pre == null) {
            count = 1;
        } else if (pre.val == cur.val) {
            count++;
        } else if (pre.val != cur.val) {
            count = 1;
        }

        pre = cur;

        // TODO:需要分开处理maxCount
        if (count == maxCount) { // 如果和最大值相同，放进result中
            result.add(cur.val);
        }

        if (count > maxCount) { // 如果计数大于最大值频率
            maxCount = count;
            result.clear();
            result.add(cur.val);
        }

        searchBST(cur.right);
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode(2);
        TreeNode root_left_right = new TreeNode(2);
        TreeNode root_left = new TreeNode(2, root_left_left, root_left_right);

        TreeNode root_right_left = new TreeNode(6);
        TreeNode root_right_right = new TreeNode(7);
        TreeNode root_right = new TreeNode(3, root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode(1, root_left, root_right);

        Tree_501 tree_94 = new Tree_501();
//        List<Integer> result  = tree_94.inorderTraversal(root);
        int[] result = tree_94.findMode(root);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }
}
