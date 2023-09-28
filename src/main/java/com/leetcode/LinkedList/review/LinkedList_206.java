package com.leetcode.LinkedList.review;

import com.leetcode.LinkedList.双指针.ListNode;

/**
 *
 */
public class LinkedList_206 {
    public ListNode reverseList(ListNode head) {
        // 如果链表为空 或者 链表只有一个节点 则不需要反转
        if (head == null || head.next == null)
            return head;
        ListNode node = reverseList(head);
        head.next.next = head;
        head.next = null;
        // 把链表反转后的头返回
        return node;
    }
}

/*
// 单链表节点的结构
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

 */
