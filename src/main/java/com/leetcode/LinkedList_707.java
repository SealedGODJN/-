package com.leetcode;

/**
 * @author HJN
 * @date 2021.9.18
 * @modify 2021.9.18
 */
public class LinkedList_707 {

    // 方案一：使用单链表
    public int val;
    public LinkedList_707 next;

    public LinkedList_707() {

    }

    /**
     * 根据index从链表中获取对应的元素，如果index是有效的，返回该值
     * 如果无效，则返回-1
     */
    public int get(int index) {

    }

    /**
     * 考虑到要在链表头插入一个节点，则引入头节点
     * @param val
     */
    public void addAtHead(int val) {

    }

    /**
     * 是否要设立尾指针，每次在尾部插入的时候，维护尾指针【插入，时间复杂度o(1)】
     * @param val
     */
    public void addAtTail(int val) {

    }

    /**
     * 在第index个节点之后添加值为val的节点
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {

    }

    /**
     * 如果Index有效，删除第index个节点
     * @param index
     */
    public void deleteAtIndex(int index) {

    }
}

/**
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
