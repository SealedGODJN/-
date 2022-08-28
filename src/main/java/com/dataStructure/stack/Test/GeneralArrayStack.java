package com.dataStructure.stack.Test;

import java.lang.reflect.Array;

public class GeneralArrayStack<T> {

    private static final int DEFAULT_SIZE = 12;
    private T[] mArray;
    private int count;

    public GeneralArrayStack(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    public GeneralArrayStack(Class<T> type, int size) {
        // 不能直接使用mArray = new T[DEFAULT_SIZE];
        mArray = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    // 将val添加到栈中
    public void push(T val) {
        if (count >= mArray.length) {
            return;
        }
        mArray[count++] = val;
    }

    // 返回“栈顶元素值”
    public T peek() {
        if (count == 0) {
            return null;
        }
        return mArray[count - 1];
    }

    // 返回“栈顶元素值”，并删除“栈顶元素”
    public T pop() {
        if (count == 0) {
            return null;
        }
        return mArray[--count];
    }

    // 返回“栈”的大小
    public int size() {
        return count;
    }

    // 返回“栈”是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 打印“栈”
    public void PrintArrayStack() {

    }

    public static void main(String[] args) {
        String tmp;
        GeneralArrayStack<String> astack = new GeneralArrayStack<String>(String.class);

        // 将10, 20, 30 依次推入栈中
        astack.push("10");
        astack.push("20");
        astack.push("30");

        // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
        tmp = astack.pop();
        System.out.println("tmp=" + tmp);

        // 只将“栈顶”赋值给tmp，不删除该元素.
        tmp = astack.peek();
        System.out.println("tmp=" + tmp);

        astack.push("40");
        astack.PrintArrayStack();    // 打印栈

        GeneralArrayStack<Integer> arrayStack = new GeneralArrayStack<Integer>(Integer.class);
        arrayStack.push(10);
        arrayStack.push(20);
        int temp = arrayStack.pop();
        System.out.println("temp=" + temp);


    }
}