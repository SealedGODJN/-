package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class HashTable_771 {
    public static int numJewelsInStones(String jewels, String stones) {
        char[] j = jewels.toCharArray();
        Set<Character> j_set = new HashSet<Character>();
        for(char c:j) {
            j_set.add(c);
        }
        char[] s = stones.toCharArray();
        int sum = 0;
        for(char c:s) {
            if(j_set.contains(c)) sum++;
        }
        return sum;
    }

    public static void main(String[] args) {
        String J = "aA", S = "aAAbbbb";
        System.out.println(numJewelsInStones(J,S));
    }
}
