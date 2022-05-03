package com.NPU.面向对象设计.chapter6;

import java.util.*;

class PrintData {
    static void print(Iterator<String> e) {
        while (e.hasNext())
            System.out.println(e.next());
    }
}

public class Iterators {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            //String.valueOf()方法将其他数据类型转成String类型
            arrayList.add(new String(String.valueOf(i)));
        }
        Vector<String> vector = new Vector<String>();
        for (int i = 0; i < 5; i++) {
            vector.add(new String(String.valueOf(i)));
        }

        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < 5; i++) {
            hashSet.add(new String(String.valueOf(i)));
        }
        LinkedList<String> linkedList = new LinkedList<String>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(new String(String.valueOf(i)));
        }
        System.out.println("----------ArrayList------");
        PrintData.print(arrayList.iterator());
        System.out.println("-----------Vector---------");
        PrintData.print(vector.iterator());
        System.out.println("-----------HashSet---------");
        PrintData.print(hashSet.iterator());
        System.out.println("-----------LinkedList---------");
        PrintData.print(linkedList.iterator());
    }
}