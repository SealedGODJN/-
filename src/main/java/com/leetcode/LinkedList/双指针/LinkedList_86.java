package com.leetcode.LinkedList.双指针;

public class LinkedList_86 {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p1 = dummy1, p2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }
        p2.next = null;
        p1.next = dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {
        LinkedList_86 linkedList86 = new LinkedList_86();
        ListNode head1 = new ListNode(1);
        int[] array1 = {5, 6, 8, 2, 4, 9, 3};
        ListNode.createListNode(head1, array1);
        ListNode result = linkedList86.partition(head1, 4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
