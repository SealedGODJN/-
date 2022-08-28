package com.dataStructure.LinkedList.Test;

/**
 * Java 实现的双向链表。
 * 注：java自带的集合包中有实现双向链表，路径是:java.util.LinkedList
 *
 * @author skywang
 * @date 2013/11/07
 */

public class DlinkTest {
    private static void string_test() {
        String[] sarr = {"ten", "twenty", "thirty", "forty"};

        System.out.println("\n----string_test----");
        // 创建双向链表
        DoubleLink dlink = new DoubleLink();

        dlink.insert(0, sarr[1]);    // 将 sarr中第2个元素 插入到第一个位置
        dlink.appendLast(sarr[0]);    // 将 sarr中第1个元素 追加到链表末尾
        dlink.insertFirst(sarr[2]);    // 将 sarr中第3个元素 插入到第一个位置

        // 双向链表是否为空
        System.out.printf("isEmpty()=%b\n", dlink.isEmpty());
        // 双向链表的大小
        System.out.printf("size()=%d\n", dlink.size());

        // 打印出全部的节点
        for (int i = 0; i < dlink.size(); i++)
            System.out.println("dlink(" + i + ")=" + dlink.get(i));
    }


    public static void main(String[] args) {
        string_test();    // 演示向双向链表操作“字符串数据”。
    }
}