package com.leetcode.LinkedList;

public class LinkedList_707_1 {
    class LinkedNode{
        int val;
        LinkedNode next;
        public LinkedNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private int size;
    private LinkedNode head;

    public LinkedList_707_1() {
        head = new LinkedNode(0); // 虚拟头节点，不是真正的表头节点
        size = 0;
    }

    public int get(int index) {
        if (index > size-1 || index < 0 ) {
            return -1;
        }
        LinkedNode cur = this.head.next;

        while ( index != 0 ) {
            index--;
            //        while ( true ) {
//            if ( --index <= 0 ) break;
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        LinkedNode newNode = new LinkedNode(val);
        newNode.next = this.head.next;
        this.head.next = newNode;

        this.size++;
    }

    public void addAtTail(int val) {
        LinkedNode newNode = new LinkedNode(val);

        LinkedNode cur = this.head;

        while ( cur.next != null ) { // cur.next == null 时，退出循环
            cur = cur.next;
        }
        cur.next = newNode;

        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if ( index > size ) {
            return;
        }

        LinkedNode newNode = new LinkedNode(val);

        LinkedNode cur = this.head;

        while ( index != 0 ) {
            index--;
            //        while ( true ) {
//            if ( --index <= 0 ) break;
            cur = cur.next;
        }

        newNode.next = cur.next;
        cur.next = newNode;

        this.size++;
    }

    public void deleteAtIndex(int index) {
        if( index > size -1 || index < 0) {
            return;
        }

        LinkedNode cur = this.head;

        while ( index != 0 ) {
            index--;
//        while ( true ) {
//            if ( --index <= 0 ) break;
            cur = cur.next;
        }
        cur.next = cur.next.next;

        this.size--;
    }

    public void printLinkedList() {
        if ( size == 0 ) return;
        LinkedNode cur = this.head.next;
        while ( cur.next != null ) {
            System.out.print(cur.val);
            System.out.print("->");
            cur = cur.next;
        }
        System.out.println(cur.val);
    }

    public static void main(String[] args) {
        LinkedList_707_1 linkedList = new LinkedList_707_1();

//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1,2);
//
//        linkedList.printLinkedList(); //
//
//        linkedList.get(1);
//        linkedList.deleteAtIndex(1);
//
//        linkedList.printLinkedList(); //
//
//        linkedList.get(1);
//        linkedList.get(3);
//        linkedList.deleteAtIndex(3);
//
//        linkedList.printLinkedList(); //
//
//        linkedList.deleteAtIndex(0);
//
//        linkedList.printLinkedList(); //
//
//        linkedList.get(0);
//        linkedList.deleteAtIndex(0);
//
//        linkedList.printLinkedList(); //
//
//        linkedList.get(0);

        linkedList.addAtHead(7);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);

        linkedList.addAtIndex(3,0);

        linkedList.printLinkedList(); //

        linkedList.deleteAtIndex(2);

        linkedList.printLinkedList(); //

        linkedList.addAtHead(6);
        linkedList.addAtTail(4);
        linkedList.get(4);

        linkedList.printLinkedList(); //

        linkedList.addAtHead(4);
        linkedList.addAtIndex(5,0);
        linkedList.addAtHead(6);

        linkedList.printLinkedList(); //

    }
}
