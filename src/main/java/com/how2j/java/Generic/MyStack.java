package com.how2j.java.Generic;

import java.util.LinkedList;

public class MyStack<T> {
    LinkedList<T> values = new LinkedList<>();

    public void push(T t) {
        this.values.addLast(t);
    }

    public T pull() {
        return this.values.removeLast();
    }

    public T peek() {
        return this.values.getLast();
    }

    public static void main(String[] args) {
        MyStack<Hero> heroMyStack = new MyStack<>();
        heroMyStack.push(new Hero("1"));
        heroMyStack.push(new Hero("2"));
        System.out.println(heroMyStack.peek());

        System.out.println(heroMyStack.pull());
    }
}
