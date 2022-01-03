package com.leetcode;

import java.util.Stack;

public class Stack_150 {
    /**
     * 中缀表达式的计算
     * @param tokens
     * @return
     */
//    public static int evalRPN(String[] tokens) {
//        Stack<String> record = new Stack<>();
//        for (String token : tokens) {
//            if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
//                record.push(token);
//            } else {
//                Integer s1 = Integer.parseInt(record.pop());
//                Integer s2 = Integer.parseInt(record.pop());
//                Integer result;
//                switch (token) {
//                    case "+":
//                        result = s1 + s2;
//                        record.push(String.valueOf(result));
//                        break;
//                    case "-":
//                        result = s2 - s1;
//                        record.push(String.valueOf(result));
//                        break;
//                    case "*":
//                        result = s1 * s2;
//                        record.push(String.valueOf(result));
//                        break;
//                    case "/":
//                        result = s2 / s1;
//                        record.push(String.valueOf(result));
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//        return Integer.parseInt(record.pop());
//    }

    int pos=0;
    /**
     * 使用递归的方式进行计算
     * @param tokens
     */
//    public int evalRPN(String[] tokens) {
//        this.pos = tokens.length - 1;
//        return eval(tokens);
//    }

//    public int eval(String[] tokens) {
//        if (this.pos == 0) {
//
//        }
//    }

    public static void main(String[] args) {
//        String[] tokens = {"2","1","+","3","*"};
        String[] tokens = {"4","13","5","/","+"};
//        System.out.println(evalRPN(tokens));
        Stack_150 stack_150 = new Stack_150();
//        System.out.println(stack_150.evalRPN(tokens));
    }
}
