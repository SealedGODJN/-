package com.dataStructure.Queue;

import java.util.LinkedList;

public class LinkedListQueue<E> {
    private LinkedList<E> data;

    public LinkedListQueue() {
        this.data = new LinkedList<>();
    }

    /**
     * 在队尾添加一个元素
     *
     * @param val
     */
    public void add(E val) {
        this.data.addLast(val);
    }

    /**
     * 取队头元素，返回队头元素（不删除队头）
     *
     * @return
     */
    public E front() {
        return this.data.getFirst();
    }

    public E pop() {
        return this.data.removeFirst();
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        int tmp = 0;
        LinkedListQueue<Integer> astack = new LinkedListQueue<>();

        // 将10, 20, 30 依次推入栈中
        astack.add(10);
        astack.add(20);
        astack.add(30);

        // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
        tmp = astack.pop();
        System.out.printf("tmp=%d\n", tmp);

        // 只将“栈顶”赋值给tmp，不删除该元素.
        tmp = astack.front();
        System.out.printf("tmp=%d\n", tmp);

        astack.add(40);

        System.out.printf("isEmpty()=%b\n", astack.isEmpty());
        System.out.printf("size()=%d\n", astack.size());
        while (!astack.isEmpty()) {
            System.out.printf("size()=%d\n", astack.pop());
        }
    }
}
