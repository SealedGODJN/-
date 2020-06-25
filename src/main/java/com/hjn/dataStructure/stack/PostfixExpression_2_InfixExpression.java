package com.hjn.dataStructure.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PostfixExpression_2_InfixExpression {
    // 用于存储运算符的优先级，key为运算符，value为对应的优先级分数（value越大，优先级越高）
    public static HashMap<Character, Integer> letter_score = new HashMap<Character, Integer>(6);
    public static ArrayList<Character> ie = new ArrayList<Character>();

    public static void main(String[] args) {
//        // 测试数字的ASCII范围
//        char c = '-';
//        int a = c;
//        System.out.println(a);

        // 初始化HashMap
        letter_score.put('+', 1);
        letter_score.put('-', 1);
        letter_score.put('*', 2);
        letter_score.put('/', 1);
        letter_score.put('(', 3);
        letter_score.put(')', 3);

        // 测试
        String test= "a+b*(c/a-z)";
        char[] pe = test.toCharArray();
        pe2ie(pe);
        for(Character c:ie) {
            System.out.print(c);
        }
    }

    public static void pe2ie(char[] pe) {
        Stack<Character> letter = new Stack<Character>();
        Stack<Character> num = new Stack<Character>();

        for(char c:pe) {
            if(c!='+' && c!='-' && c!='*' && c!='/' && c!='(' && c!= ')') {
                num.push(c);
            } else {
                if(letter.size() != 0) {
                    char compare_letter = letter.peek();
                    int compare_letter_score = letter_score.get(compare_letter);
                    int current_letter_score = letter_score.get(c);
                    if(current_letter_score>=compare_letter_score) {
                        letter.push(c);
                    } else {
                        ie.add(num.pop());
                        ie.add(num.pop());
                        ie.add(c);
                    }
                } else {
                    letter.push(c);
                }
            }
        }
    }
}
