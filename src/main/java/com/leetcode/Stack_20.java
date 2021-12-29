package com.leetcode;

import java.util.Stack;

public class Stack_20 {
    /**
     * 使用编译原理的思想来
     * @param c
     * @return
     */
//    public static boolean isValid(String s) {
////        if (s.length() == 0) return true;
//        char[] sToArray = s.toCharArray();
//        Stack<Character> record = new Stack<>();
//        for (char c : sToArray) {
//            switch (c) {
//                case '(':
//                    record.push('(');
//                    break;
//                case '[':
//                    record.push('[');
//                    break;
//                case '{':
//                    record.push('{');
//                    break;
//                case ')':
//                    if (!record.isEmpty()) {
//                        if (record.pop() != '(')
//                            return false;
//                        else break;
//                    } else return false;
//                case ']':
//                    if (!record.isEmpty()) {
//                        if (record.pop() != '[')
//                            return false;
//                        else break;
//                    } else return false;
//                case '}':
//                    if (!record.isEmpty()) {
//                        if (record.pop() != '{')
//                            return false;
//                        else break;
//                    } else return false;
//
//            }
//        }
//        return record.isEmpty();
//    }

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') st.push(')');
            else if (c[i] == '{') st.push('}');
            else if (c[i] == '[') st.push(']');
                // 第三种情况：遍历字符串匹配的过程中，栈已经为空了，没有匹配的字符了，说明右括号没有找到对应的左括号 return false
                // 第二种情况：遍历字符串匹配的过程中，发现栈里没有我们要匹配的字符。所以return false
            else if (st.empty() || st.peek() != c[i]) return false;
            else st.pop(); // st.top() 与 s[i]相等，栈弹出元素
        }
        // 第一种情况：此时我们已经遍历完了字符串，但是栈不为空，说明有相应的左括号没有右括号来匹配，所以return false，否则就return true
        return st.empty();
    }

    public static void main(String[] args) {
//        String s = "{([)[]}";
        String s = "}";
        System.out.println(isValid(s));
    }
}
