package com.leetcode;

import java.util.Stack;

public class Stack_1047 {
    public static String removeDuplicates(String s) {
        Stack<Character> record = new Stack<>();
        char[] c = s.toCharArray();
        for (char c1 : c) {
            if (!record.isEmpty()) {
                if (record.peek() == c1) record.pop();
                else record.push(c1);
            } else record.push(c1);
        }
        StringBuilder sb = new StringBuilder();
        while (!record.isEmpty()) {
            sb.append(record.pop());
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }
}

/**
 * 覆盖前一项
 */
class Solution1047 {
    public String removeDuplicates(String s) {
        if(s.length()<=1) return s;
        char[] ss = s.toCharArray();
        int top = -1;
        for(int i=0;i<ss.length;i++){
            if(top == -1 || ss[top] != ss[i]){
                ss[++top] = ss[i];
            }else{
                top--;
            }
        }
        return new String(ss,0,top+1);
    }
}
