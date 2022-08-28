package com.dataStructure.LinkedList.Test;

public class DoubleLink {

    // 表头
    private DNode head;
    // 节点个数
    private int mCount;

    // 构造函数
    public DoubleLink() {
        // 创建“表头”。注意：表头没有存储数据！
        head = new DNode(null, null, null);
        head.prev = head.next = head;
        // 初始化“节点个数”为0
        mCount = 0;
    }

    // 返回节点数目
    public int size() {
        return mCount;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return mCount == 0;
    }

    // 获取第index位置的节点
    private DNode getNode(int index) {
        if (index < 0 || index >= mCount)
            throw new IndexOutOfBoundsException();

        // 正向查找
        if (index <= mCount / 2) {
            DNode node = head.next;
            for (int i = 0; i < index; i++)
                node = node.next;

            return node;
        }

        // 反向查找
        DNode rnode = head.prev;
        int rindex = mCount - index - 1;
        for (int j = 0; j < rindex; j++)
            rnode = rnode.prev;

        return rnode;
    }

    // 获取第index位置的节点的值
    public String get(int index) {
        return getNode(index).value;
    }

    // 获取第1个节点的值
    public String getFirst() {
        return getNode(0).value;
    }

    // 获取最后一个节点的值
    public String getLast() {
        return getNode(mCount - 1).value;
    }

    // 将节点插入到第index位置之前
    public void insert(int index, String t) {
        if (index == 0) {
            DNode node = new DNode(t, head, head.next);
            head.next.prev = node;
            head.next = node;
            mCount++;
            return;
        }

        DNode inode = getNode(index);
        DNode tnode = new DNode(t, inode.prev, inode);
        inode.prev.next = tnode;
        inode.next = tnode;
        mCount++;
    }

    // 将节点插入第一个节点处。
    public void insertFirst(String t) {
        insert(0, t);
    }

    // 将节点追加到链表的末尾
    public void appendLast(String t) {
        DNode node = new DNode(t, head.prev, head);
        head.prev.next = node;
        head.prev = node;
        mCount++;
    }

    // 删除index位置的节点
    public void del(int index) {
        DNode inode = getNode(index);
        inode.prev.next = inode.next;
        inode.next.prev = inode.prev;
        inode = null;
        mCount--;
    }

    // 删除第一个节点
    public void deleteFirst() {
        del(0);
    }

    // 删除最后一个节点
    public void deleteLast() {
        del(mCount - 1);
    }
}