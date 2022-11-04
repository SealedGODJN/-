package com.dataStructure.真题.考研2021.第7题;

public class DNode {
    public DNode prev;
    public DNode next;
    public String value;

    public DNode(String value, DNode prev, DNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
