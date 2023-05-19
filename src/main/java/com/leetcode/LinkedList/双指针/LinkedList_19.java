package com.leetcode.LinkedList.双指针;

public class LinkedList_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 先找到倒数第N个节点和倒数第N个节点的前一个节点
        ListNode p = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode NthFromEnd = head;
        ListNode pre = dummy;
        while (p != null) {
            p = p.next;
            pre = NthFromEnd;
            NthFromEnd = NthFromEnd.next;
        }
        if (dummy == pre) {
            // 头节点是NthFromEnd
            if (NthFromEnd != null) {
                return NthFromEnd.next;
            } else return null;
        } else {
            pre.next = NthFromEnd.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(-2);
        int[] array1 = {-1, -1, -1};
        ListNode.createListNode(head1, array1);
        LinkedList_19 linkedList23 = new LinkedList_19();
        ListNode result = linkedList23.removeNthFromEnd(head1, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
