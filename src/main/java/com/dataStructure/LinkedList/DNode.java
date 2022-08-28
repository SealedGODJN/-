package com.dataStructure.LinkedList;

public class DNode<T> {
    public DNode prev;
    public DNode next;
    public T data; // int(Integer) double(Double) String boolean(Boolean) Student

    public DNode(T data, DNode prev, DNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public static void main(String[] args) {
        DNode<Integer> node = new DNode<>(1, null, null);

    }
}
