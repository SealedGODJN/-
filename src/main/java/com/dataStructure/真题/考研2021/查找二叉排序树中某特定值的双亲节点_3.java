package com.dataStructure.真题.考研2021;

import com.dataStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 查找二叉排序树中某特定值的双亲节点_3 {
    //    static int top = 0;
    public static void printPath(TreeNode root, String key, String path) {
        if (root != null) {
            if (root.val.equals(key)) {
                path = path.substring(0, path.length() - 1);
                System.out.println(path);
                return;
            } else {
                path += root.val + ",";
            }
//            else if ( > )
            printPath(root.left, key, path);
//            else if ( < )
            printPath(root.right, key, path);
        }
        if (root != null) {
            if (path.equals(root.val)) {
                System.out.println("没找到");
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root_left_left = new TreeNode("1");
        TreeNode root_left_right = new TreeNode("4");
        TreeNode root_left = new TreeNode("3", root_left_left, root_left_right);

        TreeNode root_right_left = new TreeNode("6");
        TreeNode root_right_right = new TreeNode("8");
        TreeNode root_right = new TreeNode("7", root_right_left, root_right_right);

//        TreeNode root_left = new TreeNode(1);
//        TreeNode root_right = new TreeNode(2);
        TreeNode root = new TreeNode("5", root_left, root_right);

        String path = "";
//        int[] path = new int[10];
        printPath(root, "6", path);
    }
}
