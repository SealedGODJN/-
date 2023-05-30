package com.leetcode.LinkedList.双指针;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
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
