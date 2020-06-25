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
        String test= "a*(b+c)";
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
                if(letter.size() != 0 && c!=')') {
                    char compare_letter = letter.peek();
                    int compare_letter_score = letter_score.get(compare_letter);
                    int current_letter_score = letter_score.get(c);
                    if(current_letter_score > compare_letter_score) { 
                        letter.push(c);
                        if(c == '(') {
                            letter_score.replace('(', 0);
                        }
                    } else { // 符号栈的运算符的优先级更高
                        // 处理符号栈的运算符
                        if(letter.peek()!= '(' && letter.peek()!=')') {
                            if(num.size() != 0) {
                                ie.add(num.pop());
                            } else {
                                System.err.println("num.size()==0");
                                break;
                            }
                            if(num.size() != 0) {
                                ie.add(num.pop());
                            } else {
                                System.err.println("num.size()==0");
                                break;
                            }
                            ie.add(letter.pop());
                        } else {
                            letter.pop();
                        }
                        if(letter.peek() == '(') {
                            letter_score.replace('(', 3);
                        }

                        // 处理当前的运算符
                        if(c!= '(' && c!=')') {
                            if(num.size() != 0) {
                                ie.add(num.pop());
                            } else {
                                System.err.println("num.size()==0");
                                break;
                            }
                            if(num.size() != 0) {
                                ie.add(num.pop());
                            } else {
                                System.err.println("num.size()==0");
                                break;
                            }
                            ie.add(c);
                        } else {
                            letter.pop();
                        }
                        if(c == '(') {
                            letter_score.replace('(', 3);
                        }
                    }
                } else if(letter.size() == 0){
                    letter.push(c);
                } else if(c == ')') { // 遇到右括号，出栈，直到遇到左括号
                    while (true) {
                        if(letter.peek()!= '(') {
                            if(num.size() != 0) {
                                ie.add(num.pop());
                            } else {
                                System.err.println("num.size()==0");
                                break;
                            }
                            if(num.size() != 0) {
                                ie.add(num.pop());
                            } else {
                                System.err.println("num.size()==0");
                                break;
                            }
                            ie.add(letter.pop());
                        } else {
                            letter.pop();
                            break;
                        }

                        if(letter.size() == 0) {
                            System.err.println("letter.size()==0");
                            return;
                        }
                    }

                }
            }
        }

        while (true) {
            Character c = letter.peek();
            if(c!= '(' && c!=')') {
                if(num.size() != 0) {
                    ie.add(num.pop());
                } else {
                    System.err.println("num.size()==0");
                    break;
                }
                ie.add(letter.pop());
                if(letter.size() == 0) {
                    System.err.println("letter.size()==0");
                    return;
                }
            }
        }
    }
}
