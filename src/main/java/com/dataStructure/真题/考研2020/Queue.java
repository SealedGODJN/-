package com.dataStructure.真题.考研2020;

public class Queue {
    Integer data;
    Queue next;

    /**
     * 如果不设置next，则next默认为null
     *
     * @param data
     */
    public Queue(Integer data) {
        this(data, null);
    }

    public Queue(Integer data, Queue next) {
        this.data = data;
        this.next = next;
    }

    /**
     * 添加一个值为data的节点，到队列head的队尾
     *
     * @param head
     * @param data
     * @return
     */
    public static Queue add(Queue head, int data) {
        Queue queue = new Queue(data);
        Queue temp = head;
        for (; temp.next != null; ) {
            temp = temp.next;
        }
        temp.next = queue;
        return head;
    }

    /**
     * 从head队头
     *
     * @param head
     * @return
     */
    public static Queue remove(Queue head) {
        if (head != null) {
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}
