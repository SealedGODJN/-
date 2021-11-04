package com.how2j.java.Collection.LinkedList;

import java.util.*;

public class TestCollection {
    public static void test1() {
        //LinkedList是一个双向链表结构的list
        LinkedList<Hero> ll =new LinkedList<Hero>();

        //所以可以很方便的在头部和尾部插入数据
        //在最后插入新的英雄
        ll.addLast(new Hero("hero1"));
        ll.addLast(new Hero("hero2"));
        ll.addLast(new Hero("hero3"));
        System.out.println(ll);

        //在最前面插入新的英雄
        ll.addFirst(new Hero("heroX"));
        System.out.println(ll);

        //查看最前面的英雄
        System.out.println(ll.getFirst());
        //查看最后面的英雄
        System.out.println(ll.getLast());

        //查看不会导致英雄被删除
        System.out.println(ll);
        //取出最前面的英雄
        System.out.println(ll.removeFirst());

        //取出最后面的英雄
        System.out.println(ll.removeLast());

        //取出会导致英雄被删除
        System.out.println(ll);
    }

    public static void test2() {
        //和ArrayList一样，LinkedList也实现了List接口
        List ll =new LinkedList<Hero>();

        //所不同的是LinkedList还实现了Deque，进而又实现了Queue这个接口
        //Queue代表FIFO 先进先出的队列
        Queue<Hero> q= new LinkedList<Hero>();

        //加在队列的最后面
        System.out.print("初始化队列：\t");
        q.offer(new Hero("Hero1"));
        q.offer(new Hero("Hero2"));
        q.offer(new Hero("Hero3"));
        q.offer(new Hero("Hero4"));

        System.out.println(q);
        System.out.print("把第一个元素取poll()出来:\t");
        //取出第一个Hero，FIFO 先进先出
        Hero h = q.poll();
        System.out.println(h);
        System.out.print("取出第一个元素之后的队列:\t");
        System.out.println(q);

        //把第一个拿出来看一看，但是不取出来
        h=q.peek();
        System.out.print("查看peek()第一个元素:\t");
        System.out.println(h);
        System.out.print("查看并不会导致第一个元素被取出来:\t");
        System.out.println(q);
    }

    private static void insertFirst(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(0, number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s 最前面插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }

    private static void insertLast(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(l.size(), number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s 最后面插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }

    private static void insertMiddle(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(l.size()/2, number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s 中间插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }

    public static void differenceOfArrayListAndLinkList() {
        List<Integer> l;
        l = new ArrayList<>();
        insertFirst(l, "ArrayList");

        l = new LinkedList<>();
        insertFirst(l, "LinkedList");

//        List<Integer> l;
        l = new ArrayList<>();
        modify(l, "ArrayList");

        l = new LinkedList<>();
        modify(l, "LinkedList");

        l = new ArrayList<>();
        insertLast(l, "ArrayList");

        l = new LinkedList<>();
        insertLast(l, "LinkedList");

        l = new ArrayList<>();
        insertMiddle(l, "ArrayList");

        l = new LinkedList<>();
        insertMiddle(l, "LinkedList");
    }

    private static void modify(List<Integer> l, String type) {
        int total = 100 * 1000;
        int index = total/2;
        final int number = 5;
        //初始化
        for (int i = 0; i < total; i++) {
            l.add(number);
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            int n = l.get(index);
            n++;
            l.set(index, n);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s总长度是%d，定位到第%d个数据，取出来，加1，再放回去%n 重复%d遍，总共耗时 %d 毫秒 %n", type,total, index,total, end - start);
        System.out.println();
    }

    public static void test3() {
        List<Object> list= new ArrayList<>();

        Object newObject = null;

        list.add(newObject); // java.lang.Object 并没有实现 Comparable 接口

        list.sort(null); // 没有抛出 java.lang.ClassCastException

    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        differenceOfArrayListAndLinkList();
        test3();
    }
}
