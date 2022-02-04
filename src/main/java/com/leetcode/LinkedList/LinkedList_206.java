package com.leetcode.LinkedList;

public class LinkedList_206 {
    /**
     * 迭代法
     * @param head
     * @return
     */
//    public static ListNode reverseList(ListNode head) {
//        if (head == null) {
//            return head;
//        }
//
//        ListNode cur = head;
//        ListNode last = null;
//
//        while ( cur != null ) {
//            ListNode temp = cur.next;
//            cur.next = last; // 链子断了，所以上一步要保存链子
//            last = cur; // 移动last指针
//            cur = temp; // cur = cur.next （但是之前cur.next被修改过了）
//        }
//        return last;
//    }

    /**
     * 递归法，每次都返回头节点
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        int[] array = { 2, 3, 4, 5};

        ListNode cur = head;

        for(int i=0; i<array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            cur.next = temp;
            temp.next = null;

            cur = temp;
        }

        head = reverseList(head);
    }
}
