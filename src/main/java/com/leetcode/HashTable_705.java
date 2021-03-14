package com.leetcode;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTable_705 {
    public int BASE = 769;
    public LinkedList[] data;
    /** Initialize your data structure here. */
    public HashTable_705() {
        data = new LinkedList[BASE]; // 不需要指定<Integer>
        for(int i=0; i<data.length; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int hash = hash(key);
        Iterator<Integer> e = data[hash].iterator();
        while (e.hasNext()){
            Integer element = e.next();
            if(element == key) {
                return;
            }
        }
//        data[hash].add(key); // 两种方法皆可
        data[hash].offerLast(key);
        return;
    }

    public void remove(int key) {
        int hash = hash(key);
        Iterator<Integer> e = data[hash].iterator();
        while (e.hasNext()) {
            Integer element = e.next();
            if(element == key) {
                data[hash].remove(element);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        Iterator<Integer> e = data[hash].iterator();
        while (e.hasNext()) {
            Integer element = e.next();
            if(element == key) {
                return true;
            }
        }
        return false;
    }

    public int hash(int key){
        return key%BASE;
    }
}


/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */