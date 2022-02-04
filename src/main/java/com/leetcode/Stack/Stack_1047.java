package com.leetcode.Stack;

import java.util.Stack;

public class Stack_1047 {
    /**
     * 使用栈来匹配前后相同的字符
     * @param s
     * @return
     */
//    public static String removeDuplicates(String s) {
//        Stack<Character> record = new Stack<>();
//        char[] c = s.toCharArray();
//        for (char c1 : c) {
//            if (!record.isEmpty() || record.peek() == c1) {
////                if (record.peek() == c1) record.pop();
////                else record.push(c1);
//                record.pop();
//            } else record.push(c1);
//        }
//        StringBuilder sb = new StringBuilder();
//        while (!record.isEmpty()) {
//            sb.append(record.pop());
//        }
//        sb.reverse();
//        return sb.toString();
//    }

    /**
     * 遇到相同的字符，则覆盖前一个字符
     * @param s
     * @return
     */
    public static String removeDuplicates(String s) {
        char[] c = s.toCharArray();
        int slow = -1;
//        for (; slow < c.length - 1; slow++) {
//            if (slow > 0 && c[slow] == c[slow-1]) {
//                len--;
//                fast = slow + 1;
//                c[slow] = c[fast];
//            }
//        }
        for (int i = 0; i < c.length; i++) {
            if (slow == -1 || c[slow] != c[i]) { // 遍历所有的内容
                c[++slow] = c[i]; // slow模拟栈的指针，i是数组的指针
            } else {
                slow--; // 为下一次操作的覆盖
            }
        }
        return new String(c, 0, slow+1);
    }

    public static void main(String[] args) {
        String s = "abbccaca";
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
