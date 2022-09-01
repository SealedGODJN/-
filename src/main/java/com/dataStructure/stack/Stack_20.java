package com.dataStructure.stack;

import java.util.Stack;

public class Stack_20 {
    public static boolean isValid(String s) {
        char[] sToArray = s.toCharArray();
        Stack<Character> record = new Stack<>();
        for (char c : sToArray) {
            switch (c) {
                case '(':
                    record.push('(');
                    break;
                case '[':
                    record.push('[');
                    break;
                case '{':
                    record.push('{');
                    break;
                case ')':
                    if (!record.isEmpty()) {
                        if (record.pop() != '(')
                            return false;
                        else break;
                    } else return false;
                case ']':
                    if (!record.isEmpty()) {
                        if (record.pop() != '[')
                            return false;
                        else break;
                    } else return false;
                case '}':
                    if (!record.isEmpty()) {
                        if (record.pop() != '{')
                            return false;
                        else break;
                    } else return false;

            }
        }
        return record.isEmpty();
    }

    public static void main(String[] args) {
        String s = "()[]{}";
        String s1 = "(]";
        String s2 = "([)]";
        System.out.println(isValid(s));
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
    }
}
