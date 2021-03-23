package com;

public class stack {
    private int[] data; // 数据
    int top; // 栈顶指针

    public stack(int length){
        this.data = new int[length];
        this.top = -1;
    }

    public void push(int value){
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        this.top++; // 栈顶指针向上移动
        data[top] = value;
    }

    // 栈为空，返回-1
    // 否则返回top对应的值
    public int pop() {
        if(isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }
        int value = data[top];
        data[top] = 0; // 还原至默认值
        this.top--;
        return value;
    }

    public int size() {
        return this.top+1;
    }

    public boolean isEmpty() {
        return this.top==-1;
    }

    public boolean isFull() {
        return this.top == data.length-1;
    }

    public static void main(String[] args) {
        int length = 5;
        stack s = new stack(length);
        System.out.println("是否为空"+s.isEmpty());
        for(int i=0; i<length; i++) {
            s.push(i);
        }
        System.out.println("长度为"+s.size());
        System.out.println("是否为空"+s.isEmpty());
        System.out.println("是否为满"+s.isFull());
        for(int i=0; i<length; i++) {
            System.out.println(s.pop());
        }
    }
}
