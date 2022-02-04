package com.leetcode.Stack;

import java.util.EmptyStackException;
import java.util.Stack;

public class Stack_232 {
    public static void main(String[] args) {
        int x = 10;
        MyQueue obj = new MyQueue();

        obj.push(x); // 10
        obj.push(x+1); // 11
        obj.push(x+2); // 12

        int param_2 = obj.pop(); // 10
        int param_3 = obj.pop(); // 11
        int param_4 = obj.pop(); // 10

        boolean param_6 = obj.empty();

        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
        System.out.println(param_6);

        obj.push(x); // 10
        obj.push(x+1); // 11
        obj.push(x+2); // 12

        int param_5 = obj.pop(); // 10

        System.out.println(param_5);

    }
}

class MyQueue {
    Stack<Integer> stackA;
    Stack<Integer> stackB;
    public MyQueue() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {
        stackA.push(x);
    }

    public int pop() {
        if( stackB.isEmpty() ) { // B是空的，A放倒B中
            while (!stackA.isEmpty()) {
                int temp = stackA.pop();
                stackB.push(temp);
            }
        }
        int result;
        if (!stackB.isEmpty()) {
            result = stackB.pop();
        } else throw new EmptyStackException();

        return result;
    }

    public int peek() {
        if( stackB.isEmpty() ) { // B是空的，A放倒B中
            while (!stackA.isEmpty()) {
                int temp = stackA.pop();
                stackB.push(temp);
            }
        }
        int result;
        if (!stackB.isEmpty()) {
            result = stackB.peek();
        } else throw new EmptyStackException();

        return result;
    }

    public boolean empty() {
        return stackA.empty() && stackB.empty(); // 为什么是 && 而不是 ||？
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */