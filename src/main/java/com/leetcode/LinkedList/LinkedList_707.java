package com.leetcode.LinkedList;

/**
 * @author HJN
 * @date 2021.9.18
 * @modify 2021.9.18
 */
public class LinkedList_707 {

    // 方案一：使用单链表
    public int val;
    public LinkedList_707 next;
    public int length; // 管理长度
    public LinkedList_707 tail; // 尾指针

    /** Initialize your data structure here. */
    public LinkedList_707() {
        this.val = 0; // val的有效值是0-1000
        this.next = null;
        this.tail = this;
        this.length = 0;
    }

    /**
     * 根据index从链表中获取对应的元素，如果index是有效的，返回该值
     * 如果无效，则返回-1
     */
    public int get(int index) {
        if ( index < 0 || index > length -1 ) return -1;
        LinkedList_707 cur = this;
        while ( true ) {
            index--;
            if ( index == -1 ) return cur.val;
            cur = cur.next; // 找下一个节点
        }
    }

    /**
     * 考虑到要在链表头插入一个节点，则引入头节点
     * @param val
     */
    public void addAtHead(int val) {
        if(this.length == 0) { // 链表为空
            this.val = val;
            this.length++; // 维护长度
            return;
        }

        //链表不为空
        LinkedList_707 temp = this.next;

        LinkedList_707 newNode = new LinkedList_707();

        // 把newNode 插入到第一个和第二个之间
        newNode.next = temp;
        if(temp == null) this.tail = newNode; // 维护尾指针
        this.next = newNode;
        newNode.val = this.val;
        this.val = val;

        newNode.tail = this.tail; // 维护尾指针
        this.length++; // 维护长度
        newNode.length = this.length; // 维护长度

    }

    /**
     * 是否要设立尾指针，每次在尾部插入的时候，维护尾指针【插入，时间复杂度o(1)】
     * 不适合维护尾指针？
     * @param val
     */
    public void addAtTail(int val) {
        if(length == 0) {
            this.val = val;
            this.length++;
            return;
        }
        LinkedList_707 newNode = new LinkedList_707();
        newNode.val = val;

        this.tail.next = newNode; // 插入到尾指针的next？
        this.tail = newNode; // 维护尾指针
        newNode.next = null;
        this.length++; // 维护长度

    }

    /**
     * 在第index个节点之前添加值为val的节点
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        if ( index > length ) return;
        if ( index <= 0 ) {
            this.addAtHead(val);
            return;
        }
        if ( index == length ) {
            this.addAtTail(val);
            return;
        }

        LinkedList_707 head = new LinkedList_707();
        head.next = this;
        LinkedList_707 cur = this;
        LinkedList_707 pre = head;
        while ( true ) { // 条件代表什么？
            index--;
            if ( index == -1 ) {
                LinkedList_707 newNode = new LinkedList_707();
                newNode.val = val;
                pre.next = newNode;
                newNode.next = cur;

                newNode.tail = this.tail; // 维护尾指针
                this.length++; // 维护长度
                newNode.length = this.length; // 维护长度

                return;
            }
            pre = cur;
            cur = cur.next;
        }
    }

    /**
     * 如果Index有效，删除第index个节点
     * @param index
     */
    public void deleteAtIndex(int index) {
        if ( index < 0 || index > length ) return;

        LinkedList_707 head = new LinkedList_707();
        head.next = this;
        LinkedList_707 cur = this;
        LinkedList_707 pre = head;

        // 删除头节点
        // 把所有的节点的值往前移，并移除最后一个节点
        if ( index == 0 ) {

            while( cur != null) {
                if (cur.next != null) {
                    cur.val = cur.next.val;
                } else {
                    pre.next = null;
                    this.length--;
                    return;
                }

                pre = cur;
                cur = cur.next;
            }
            this.length--;
            return;
        }

        while ( pre.next != null) {
            index--;
            if ( index == -1 ) {
                pre.next = cur.next;
                if(cur.next == null) this.tail = pre; // 维护尾指针
                this.length--; // 维护长度

                return;
            }
            pre = cur;
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        LinkedList_707 linkedList = new LinkedList_707();
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//        linkedList.get(1);            //返回2
//        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//        linkedList.get(1);            //返回3

        // addAtHead(7)出错【未考虑length==0】，addAtIndex(3,0)多执行了一次
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(1);
//        linkedList.addAtIndex(3,0);
//        linkedList.deleteAtIndex(2);;
//        linkedList.addAtHead(6);
//        linkedList.addAtTail(4);
//        linkedList.get(4);
//        linkedList.addAtHead(4);
//        linkedList.addAtIndex(5,0);
//        linkedList.addAtHead(6);

        // deleteAtIndex(0)的问题，删除头节点
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1,2);
//        linkedList.get(1);
//        linkedList.deleteAtIndex(0);
//        linkedList.get(0);

        // addAtTail的问题【未考虑length==0】
//        linkedList.addAtTail(1);
//        linkedList.get(0);

        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);
        linkedList.get(1);
        linkedList.deleteAtIndex(1);
        linkedList.get(1);
        linkedList.get(3);
        linkedList.deleteAtIndex(3);
        linkedList.deleteAtIndex(0);
        linkedList.get(0);
        linkedList.deleteAtIndex(0);
        linkedList.get(0);

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
