package com.dataStructure.LinkedList.Test;

public class LNode {
    public String data;
    public LNode next;

    public LNode(String str) {
        this.data = str;
        this.next = null;
    }

    public LNode(String str, LNode next) {
        this.data = str;
        this.next = next;
    }

    public static void main(String[] args) {
        LNode node1 = new LNode("1");
        LNode node2 = new LNode("2");
        node1.next = node2;
        node2.next = null;
    }

}