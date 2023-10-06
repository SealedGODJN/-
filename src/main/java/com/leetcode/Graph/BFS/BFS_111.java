package com.leetcode.Graph.BFS;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BFS_111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 1;
        int result = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.peek();
                q.poll();
                if (cur.left == null && cur.right == null) {
//                return count;
                    result = Math.min(result, count);
                }
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }

            count++;
        }
        return count;
    }
}
