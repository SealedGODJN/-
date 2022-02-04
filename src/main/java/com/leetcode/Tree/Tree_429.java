package com.leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree_429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<Node> level = new ArrayDeque<>();
        level.push(root);
        while (!level.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
            List<Node> waitToAdd = new ArrayList<>();
            while (!level.isEmpty()) {
                Node temp = level.pop();
                if (temp.children != null) {
                    for (Node child : temp.children) {
                        waitToAdd.add(child);
                    }
                }
                levelResult.add(temp.val);
            }
            level.addAll(waitToAdd);
            result.add(levelResult);
        }
        return result;
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

        Tree_429 tree_429 = new Tree_429();
        List<List<Integer>> result  = tree_429.levelOrder(root);
        for (List<Integer> level : result) {
            for (Integer integer : level) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
