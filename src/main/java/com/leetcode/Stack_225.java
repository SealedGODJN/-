package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Stack_225 {
    public static void main(String[] args) {
        int x = 0;
        MyStack obj = new MyStack();
        obj.push(x);
        obj.push(x+1);
        obj.push(x+2);
        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
    }
}

class MyStack {
    Deque<Integer> first;
    Deque<Integer> second;

    public MyStack() {
        first = new ArrayDeque<>();
        second = new ArrayDeque<>();
    }

    public void push(int x) {
        first.addLast(x);
    }

    public int pop() {
        if (!first.isEmpty()) {
            while (true) {
                // boxing
                int x = Integer.parseInt(String.valueOf(first.removeFirst()));
                if (first.isEmpty()) {
//                    break;
                    return x;
                }
                second.addLast(x);
            }
        } else {
            while (true) {
                // boxing
                int x = Integer.parseInt(String.valueOf(second.removeFirst()));
                if (second.isEmpty()) {
//                    break;
                    return x;
                }
                first.addLast(x);
            }
        }
    }

    public int top() {
        if (!first.isEmpty()) {
            int x = -1;
            while (!first.isEmpty()) {
                // boxing
                x = Integer.parseInt(String.valueOf(first.removeFirst()));
//                if (!first.isEmpty()) {
////                    break;
//                    return x;
//                }
                second.addLast(x);
            }
            return x;
        } else {
            int x = -1;
            while (!second.isEmpty()) {
                // boxing
                 x = Integer.parseInt(String.valueOf(second.removeFirst()));
//                if (!second.isEmpty()) {
////                    break;
//                    return x;
//                }
                first.addLast(x);
            }
            return  x;
        }
    }

    public boolean empty() {
        return first.isEmpty() && second.isEmpty();
    }
}
