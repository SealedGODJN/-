package com.leetcode.LinkedList.双指针;

public class LinkedList_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // 虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    // 一直往后走，可以走到null
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        int[] array1 = {1};
        ListNode.createListNode(head1, array1);

        LinkedList_83 linkedList86 = new LinkedList_83();
        ListNode result = linkedList86.deleteDuplicates(head1);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
