package com.leetcode.Graph.BFS;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BFS_752 {
    private String plusOne(String s, int index) {
        char[] seq = s.toCharArray();
        // 0 - 9 循环 旋转
        if (seq[index] == '9') {
            seq[index] = '0';
        } else {
            seq[index]++;
        }
        return new String(seq);
    }

    private String minusOne(String s, int index) {
        char[] seq = s.toCharArray();
        // 0 - 9 循环 旋转
        if (seq[index] == '0') {
            seq[index] = '9';
        } else {
            seq[index]++;
        }
        return new String(seq);
    }


    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for (String s : deadends) {
            dead.add(s);
        }
        Set<String> visited = new HashSet<>();

        Deque<String> q = new LinkedList<>();
        q.push("0000");
        visited.add("0000");

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String cur = q.pop();

                if (dead.contains(cur)) continue;
                if (cur.equals(target)) return step;

                for (int j = 0; j < 4; j++) {
                    String plusOne = plusOne(cur, j);
                    if (!visited.contains(plusOne)) {
                        q.push(plusOne);
                        visited.add(plusOne);
                    }
                    String minusOne = minusOne(cur, j);
                    if (!visited.contains(minusOne)) {
                        q.push(minusOne);
                        visited.add(minusOne);
                    }
                }
            }
            step++;
        }

        return -1;
    }
}
