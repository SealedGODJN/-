package com.leetcode.Design;

import java.util.HashMap;
import java.util.Map;

public class Design_146_DoubleLink {
    public static void main(String[] args) {
        LRUCache_DLink lRUCache = new LRUCache_DLink(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}

class DNode {
    int key;
    int value;
    DNode pre;
    DNode next;

    public DNode() {
        this.pre = null;
        this.next = null;
    }

    public DNode(int k, int v, DNode pre, DNode next) {
        this.key = k;
        this.value = v;
        this.pre = pre;
        this.next = next;
    }
}

class DLink {
    DNode head;
    DNode tail;

    public DLink() {
        head = new DNode();
        tail = new DNode();
        head.pre = tail;
        head.next = tail;
        tail.pre = head;
        tail.next = head;
    }

    /**
     * 移动到尾节点前面
     *
     * @param node
     */
    public void moveToTail(DNode node) {
        // 1、从当前位置删除
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // 2、移到尾节点前面
        node.next = tail;
        node.pre = tail.pre;
        tail.pre.next = node;
        tail.pre = node;
    }

    /**
     * 插入到尾节点前面
     *
     * @param key
     * @param value
     * @return
     */
    public DNode addToTail(int key, int value) {
        DNode node = new DNode(key, value, tail.pre, tail);
        tail.pre.next = node;
        tail.pre = node;
        return node;
    }
}

class LRUCache_DLink {
    int capacity;

    DLink allValues;

    Map<Integer, DNode> cache;

    public LRUCache_DLink(int capacity) {
        this.capacity = capacity;
        allValues = new DLink();
        cache = new HashMap<>();
    }

    public int get(int key) {
        DNode tmp = cache.getOrDefault(key, null);
        if (tmp != null) {
            allValues.moveToTail(tmp);
            return tmp.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DNode tmp = cache.get(key);
            tmp.value = value;
            allValues.moveToTail(tmp);
        } else {
            if (capacity == 0) {
                // 删除cache中头节点的下一个节点
                cache.remove(allValues.head.next.key);
                allValues.head.next.key = key;
                allValues.head.next.value = value;
                cache.put(key, allValues.head.next);
                allValues.moveToTail(allValues.head.next);
            } else {
                cache.put(key, allValues.addToTail(key, value));
                capacity--;
            }
        }
    }
}