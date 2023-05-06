package com.leetcode.Design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Design_460_LinkedHashSet {
    public static void main(String[] args) {
        // cnt(x) = 键 x 的使用计数
        // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
        LFUCache lfu = new LFUCache(2);
        lfu.put(3, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 1);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//        System.out.println(lfu.get(1));      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(2, 2);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
//        System.out.println(lfu.get(2));      // 返回 -1（未找到）
//        System.out.println(lfu.get(3));      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(2));      // 返回 -1（未找到）
//        System.out.println(lfu.get(3));      // 返回 3
//        // cache=[3,4], cnt(4)=1, cnt(3)=3
//        System.out.println(lfu.get(4));      // 返回 4
//        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }
}

class LFUCache {
    Map<Integer, Node> cache;  // 存储缓存的内容
    Map<Integer, LinkedHashSet<Node>> freqMap; // 存储每个频次对应的双向链表
    int size;
    int capacity;
    int min; // 存储当前最小频次

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.min = 0;
        // HashMap可以指定长度
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.getOrDefault(key, null);
        if (node == null) return -1;
        freqPlus(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            Node tmp = cache.get(key);
            tmp.value = value;
            // 不能重置，因为键 key 已存在，则变更其值（此时使用频率+1）
//            tmp.freq = 0;
            freqPlus(tmp);
        } else {
            if (capacity == size) {
                // 删除最近最久未使用的node
                Node deadNode = removeNode();
                cache.remove(deadNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;

        }
    }

    public void freqPlus(Node node) {
        // 从原freq对应的链表里移除, 并更新min
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0) {
            // 更新最小频率
            min++;
        }
        // 加入新freq对应的链表
        node.freq++;
        LinkedHashSet<Node> nextSet = freqMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>());
        nextSet.add(node);
    }

    public void addNode(Node node) {
        LinkedHashSet<Node> set = freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>());
        set.add(node);
        min = 1;
    }

    public Node removeNode() {
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }
}

class Node {
    int key;
    int value;
    int freq;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }
}