package com.leetcode;

import java.util.HashMap;

public class HashMap_383 {
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> sum = new HashMap<>(); // key是ransomNote的字符，value是对应的出现再randomNote中的次数
        char[] temp1 = ransomNote.toCharArray();
        for (char c : temp1) {
            int value = sum.getOrDefault(c, 0);
            sum.put(c, ++value);
        }
        char[] temp2 = magazine.toCharArray();
        for (char c : temp2) {
            if (sum.containsKey(c)) {
                int value = sum.get(c);
                sum.replace(c, --value);
            }
        }
        for (Integer i :
                sum.values()) {
            if (i>0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }
}
