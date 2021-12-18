package com.leetcode;

import java.util.EmptyStackException;
import java.util.Stack;

public class Stack_232 {
    public static void main(String[] args) {
        int x = 10;
        MyQueue obj = new MyQueue();
        obj.push(x);
        obj.push(x+1);
        obj.push(x+2);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
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
        while (!stackA.isEmpty()) {
            int temp = stackA.pop();
            stackB.push(temp);
        }
        int result = -1;
        if (!stackB.isEmpty()) {
             result = stackB.pop();
        } else throw new EmptyStackException();
        while (!stackB.isEmpty()) {
            int temp = stackB.pop();
            stackA.push(temp);
        }
        return result;
    }

    public int peek() {
        while (!stackA.isEmpty()) {
            int temp = stackA.pop();
            stackB.push(temp);
        }
        int result = -1;
        if (!stackB.isEmpty()) {
            result = stackB.peek();
        } else throw new EmptyStackException();
        while (!stackB.isEmpty()) {
            int temp = stackB.pop();
            stackA.push(temp);
        }
        return result;
    }

    public boolean empty() {
        return stackA.empty();
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