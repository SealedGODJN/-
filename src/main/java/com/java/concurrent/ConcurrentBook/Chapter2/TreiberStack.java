package com.java.concurrent.ConcurrentBook.Chapter2;

import java.util.concurrent.atomic.AtomicReference;

public class TreiberStack<E> {
    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();

    public void push(E item) {//总是在堆顶加入新节点
        Node<E> node = new Node<E>(item);
        Node<E> old;
        do {
            old = head.get();
            node.next = old;
        } while (!head.compareAndSet(old, node));
    }

    public E pop() {//总是从堆顶移除
        Node<E> old;
        Node<E> node;
        do {
            old = head.get();
            if (old == null)
                return null;
            node = old.next;
        } while (!head.compareAndSet(old, node));
        return old.item;
    }

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
