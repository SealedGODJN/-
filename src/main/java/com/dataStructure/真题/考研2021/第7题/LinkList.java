package com.dataStructure.真题.考研2021.第7题;

import java.util.Scanner;

public class LinkList {

    // 表头
    public LNode head;
    // 节点个数
    public int mCount;

    // 构造函数
    public LinkList() {
        // 创建“表头”。注意：表头没有存储数据！
        this.head = new LNode("");
        // 初始化“节点个数”为0
        this.mCount = 0;
    }

    // 头插法 （逆向）建立单链表
    public void HeadInsert() {
        // 在控制台中输入一行内容，s可以读取控制台中输入的内容
        Scanner s = new Scanner(System.in);
        String str = s.nextLine(); // 返回当前输入行的内容

        // 判断str 是否 == 空的字符串
        while (!str.equals("")) {
            // 在链表的头节点后面新增节点
            LNode node = new LNode(str);
            node.next = this.head.next;
            this.head.next = node;
            this.mCount++;

            // 为下一个节点的data做准备
            str = s.nextLine();
        }
    }

    // 将节点插入到第index位置之前
    public void insert(int index, String t) {
    }

    public DoubleLink tranDLNode(LinkList list) {
        if (list != null) {
            if (list.head.next != null) {
                LNode nextLNode = list.head.next;
                DoubleLink doubleLink = new DoubleLink();
                DNode rear = doubleLink.head; // 尾指针
                while (nextLNode != null) {
                    DNode dNode = new DNode(nextLNode.data, null, null);
//                    dNode.value = nextLNode.data;
                    rear.next = dNode;
                    dNode.prev = rear;
                    rear = dNode; // 更新尾指针

                    nextLNode = nextLNode.next;
                }
                return doubleLink;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkList L = new LinkList();
        L.HeadInsert();
    }
}
