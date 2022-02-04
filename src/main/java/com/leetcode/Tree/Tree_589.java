package com.leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class Tree_589 {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    /**
     * 递归遍历树
     * @param root 一棵树的根节点
     * @param result 树的前序遍历的结果
     */
    public void traversal(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                traversal(child, result);
            }
        }
    }

    public static void main(String[] args) {
        Node root_left_left = new Node(5);
        Node root_left_right = new Node(6);
//        Node root_left = new Node(2, root_left_left, root_left_right);
        Node root_left = new Node(3);

        root_left.children = new ArrayList<>();

        root_left.children.add(root_left_left);
        root_left.children.add(root_left_right);

        Node root_rightright = new Node(4);
//        Node root_right = new Node(3, root_right_left, root_right_right);
        Node root_right = new Node(2);

//        Node root_left = new Node(1);
//        Node root_right = new Node(2);
//        Node root = new Node(3, root_left, root_right);
        Node root = new Node(1);

        root.children = new ArrayList<>();

        root.children.add(root_left);
        root.children.add(root_right);
        root.children.add(root_rightright);

        Tree_589 tree_589 = new Tree_589();
        List<Integer> result  = tree_589.preorder(root);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
