package com.leetcode.LinkedList.双指针;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class LinkedList_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            // 为什么条件不能是p1.next == null？
            // 如果不相交，那p1 和 p2都把各自的链表都走一遍
            // 此时判断p1.next == null和p2.next == null，会导致p1和p2都到达不了null（两个链表最后的一个节点，无法退出循环）
            if (p1 == null) {
                p1 = headB;
            } else p1 = p1.next;
            ;
            if (p2 == null) {
                p2 = headA;
            } else p2 = p2.next;
            ;

        }
        return p1;
    }

    public static void main(String[] args) {
        LinkedList_160 linkedList160 = new LinkedList_160();

        /**
         * 测试用例1 相交
         */
//        ListNode head = new ListNode(4);
//        int[] array = {1, 8, 4, 5};
//        ListNode.createListNode(head, array);
//        ListNode head1 = new ListNode(5);
//        int[] array1 = {6, 1};
//        ListNode.createListNode(head1, array1);
        // 相交
//        head1.next.next.next = head.next.next;

        /**
         * 测试用例2 不相交
         */
        ListNode head = new ListNode(2);
        int[] array = {4, 6};
        ListNode.createListNode(head, array);
        ListNode head1 = new ListNode(1);
        int[] array1 = {5, 6, 8};
        ListNode.createListNode(head1, array1);

        System.out.println(linkedList160.getIntersectionNode(head, head1).val);
    }
}