package com.dataStructure.LinkedList.Test;

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
