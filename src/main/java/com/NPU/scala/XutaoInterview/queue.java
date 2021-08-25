package com.NPU.scala.XutaoInterview;

/**
 * 循环队列
 * 问题：
 * 判断队空和队满
 * 最简单的方法：
 * 1、tag=0（出队时为0，只有出队会导致队列为空）/=1（入队时为1，只有入队会导致队列为满）
 * 2、取余操作
 */
public class queue {
    public int[] data;
    public int front;
    public int rear;
    public queue(int length) {
        this.data = new int[length];
        this.front = 0;
        this.rear = 0; // 队尾指针指向队尾元素的下一个位置（而非指向队尾元素）
    }

    public boolean isFull() {
        return (front == (rear+1)%this.data.length);// || (rear+1);
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public int size() {
        return (this.data.length-front+rear) % this.data.length;
    }

    public void enqueue(int value) {
        if(isFull()) {
            System.out.println("循环队列满");
            return;
        }
        this.data[rear] = value;
        rear = (rear+1)%this.data.length;
    }

    public int dequeue() {
        if(isEmpty()) {
            System.out.println("循环队列为空");
            return -1;
        }
        int value = this.data[front];
        front = (front+1)%this.data.length;
        return value;
    }

    public static void main(String[] args) {
        int length = 5;
        queue q = new queue(length);
        System.out.println("是否为空"+q.isEmpty());
        for(int i=0; i<length; i++) {
            q.enqueue(i);
        }
        System.out.println("长度为"+q.size());
        System.out.println("是否为空"+q.isEmpty());
        System.out.println("是否为满"+q.isFull());
        for(int i=0; i<length; i++) {
            System.out.println(q.dequeue());
        }
    }
}
