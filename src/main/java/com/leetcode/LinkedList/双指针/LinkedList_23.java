package com.leetcode.LinkedList.双指针;

import java.util.List;
import java.util.PriorityQueue;

public class LinkedList_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> que = new PriorityQueue<>(lists.length, (a, b) -> (a.val - b.val));
        ListNode[] temp = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            temp[i] = lists[i];
            while (temp[i] != null) {
                que.add(temp[i]);
                temp[i] = temp[i].next;
            }
        }
        while (!que.isEmpty()) {
            ListNode tmp = que.poll();
            p.next = new ListNode(tmp.val);
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
//        ListNode head1 = new ListNode(1);
//        int[] array1 = {4, 5};
//        ListNode.createListNode(head1, array1);
//
//        ListNode head2 = new ListNode(1);
//        int[] array2 = {3, 4};
//        ListNode.createListNode(head2, array2);
//
//        ListNode head3 = new ListNode(2);
//        int[] array3 = {6};
//        ListNode.createListNode(head3, array3);

        // 测试用例2
        // -2,-1,-1,-1
        // []
        ListNode head1 = new ListNode(-2);
        int[] array1 = {-1, -1, -1};
        ListNode.createListNode(head1, array1);
        ListNode head2 = null;
        ListNode[] lists = {head1, head2};

        LinkedList_23 linkedList23 = new LinkedList_23();
        ListNode result = linkedList23.mergeKLists(lists);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
