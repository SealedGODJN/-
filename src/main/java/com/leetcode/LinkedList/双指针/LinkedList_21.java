package com.leetcode.LinkedList.双指针;

public class LinkedList_21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = list1, p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedList_21 linkedList160 = new LinkedList_21();

        /**
         * 测试用例1
         * 2，4，6
         * 1，5，6，8
         */
        ListNode head = new ListNode(2);
        int[] array = {4, 6};
        ListNode.createListNode(head, array);
        ListNode head1 = new ListNode(1);
        int[] array1 = {5, 6, 8};
        ListNode.createListNode(head1, array1);

        ListNode result = linkedList160.mergeTwoLists(head, head1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }


}


