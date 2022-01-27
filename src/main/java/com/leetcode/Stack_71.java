package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack_71 {
    public String simplifyPath(String path) {
        String[] pathArray = path.split("/+");
        // 使用队列记录目录
        Deque<String> result = new ArrayDeque<>();

        for (String c : pathArray) {
            if (".".equals(c)) {
                // "."表示当前目录
                continue;
            } else if ("..".equals(c)) {
                // 回到上一级目录
                if (!result.isEmpty()) {
                    result.removeLast();
                }
            } else if (!c.isEmpty()) {
                // c是文件名
                result.addLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : result) {
            sb.append("/");
            sb.append(s);
        }

        return sb.toString().length() == 0? "/": sb.toString();
    }

    public static void main(String[] args) {
        Stack_71 stack_71 = new Stack_71();
//        String path = "/home/";
//        String path = "/home//foo/";
//        String path = "/a/./b/../../c/";
        String path = "/../";

        System.out.println(stack_71.simplifyPath(path));

    }
}
