package com.hjn.dataStructure.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PostfixExpression_2_InfixExpression {
    // 用于存储运算符的优先级，key为运算符，value为对应的优先级分数（value越大，优先级越高）
    public static HashMap<Character, Integer> letter_score = new HashMap<Character, Integer>(6);
    public static Stack<Character> letter = new Stack<Character>();
    public static Stack<Character> num = new Stack<Character>();

    public static void main(String[] args) {
//        // 测试数字的ASCII范围
//        char c = '-';
//        int a = c;
//        System.out.println(a);

        // 初始化HashMap
        letter_score.put('+', 1);
        letter_score.put('-', 1);
        letter_score.put('*', 2);
        letter_score.put('/', 2);
        letter_score.put('(', 3);
        letter_score.put(')', 3);

        // 测试
        String test= "(3+2)*(9+6/3-5)+4";
        char[] pe = test.toCharArray();
        pe2ie(pe);
        for(Character c:num) {
            System.out.print(c);
        }
    }

    public static void pe2ie(char[] pe) {

        for(char c:pe) {
            if(c!='+' && c!='-' && c!='*' && c!='/' && c!='(' && c!= ')') {
                num.push(c);
            } else {
                if(letter.size() != 0 && c!=')') {
                    while (true) {
                        char compare_letter;
                        if(letter.size() != 0) {
                            compare_letter = letter.peek();
                        } else {
                            letter.push(c);
                            if(c == '(') {
                                letter_score.replace('(', 0);
                            }
                            break;
                        }

                        int compare_letter_score = letter_score.get(compare_letter);
                        int current_letter_score = letter_score.get(c);
                        if(current_letter_score > compare_letter_score) {
                            letter.push(c);
                            if(c == '(') {
                                letter_score.replace('(', 0);
                            }
                            break;
                        } else { // 符号栈的运算符的优先级更高
                            // 处理符号栈的运算符
                            if(letter.peek()!= '(' && letter.peek()!=')') {
                                num.push(letter.pop());
                            } else {
                                letter.pop();
                            }
                        }
                    }

                } else if(letter.size() == 0){
                    letter.push(c);
                } else if(c == ')') { // 遇到右括号，出栈，直到遇到左括号
                    while (true) {
                        if(letter.peek()!= '(') {
                            num.push(letter.pop());
                        } else {
                            letter.pop();
                            letter_score.replace('(',3);
                            break;
                        }

                        if(letter.size() == 0) {
//                            System.err.println("letter.size()==0");
                            break;
                        }
                    }

                }
            }
        }

        while (true) {
            if(letter.size() != 0) {
                Character c = letter.peek();
                if(c!= '(' && c!=')') {
                    num.push(letter.pop());
                    if(letter.size() == 0) {
//                        System.err.println("letter.size()==0");
                        return;
                    }
                }
            } else {
                break;
            }

        }
    }
}
