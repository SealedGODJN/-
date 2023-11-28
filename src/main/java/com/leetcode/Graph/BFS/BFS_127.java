package com.leetcode.Graph.BFS;

import java.util.*;

public class BFS_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.isEmpty() || !wordSet.contains(endWord)) return 0;

        // key-word value-路径长度
        Map<String, Integer> visited = new HashMap<>();
        visited.put(beginWord, 1);

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        while (!q.isEmpty()) {
            String word = q.poll();

            int path = visited.get(word);

            for (int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                for (char k = 'a'; k <= 'z'; k++) {
                    //替换第i个字符
                    wordArray[i] = k;
                    String newWord = String.valueOf(wordArray);
                    if (newWord.equals(endWord)) return path + 1;
                    // newWord 要在 wordSet中 并且没有被访问过
                    if (wordSet.contains(newWord) && !visited.containsKey(newWord)) {
                        // path 要 +1
                        visited.put(newWord, path + 1);
                        // 广度优先遍历
                        q.add(newWord);
                    }
                }
            }
        }
        return 0;
    }
}
