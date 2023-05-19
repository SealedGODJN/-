package com.leetcode.LinkedList.双指针;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public static void createListNode(ListNode head, int[] array) {
        ListNode cur = head;
        for (int j : array) {
            ListNode temp = new ListNode(j);
            cur.next = temp;
            temp.next = null;

            cur = temp;
        }
    }
}
