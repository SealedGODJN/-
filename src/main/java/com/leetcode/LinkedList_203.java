package com.leetcode;

import java.util.List;

public class LinkedList_203 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
//    public static ListNode removeElements(ListNode head, int val) {
//        if(head == null) return null;
//
//        ListNode head_temp = new ListNode(-1, head); // 虚拟头节点（原链表是不带头节点的）
//        ListNode cur = head_temp.next;
//        ListNode pre = head_temp;
//        while (cur != null) {
//            if(cur.val == val) { // 删除cur
//                pre.next = cur.next;
//                cur.val = -1; // 置该节点为空，-1即为空
//                cur = pre.next; // pre不变，cur变为pre.next
//            }
//            else {
//                pre = cur;
//                cur = cur.next;
//            }
//        }
//        // 这句比较多余
////        if(head_temp.next == null) return null;
//        return head_temp.next;
//    }

//    /**
//     * 时间复杂度为O(n)
//     * 空间复杂度为O(n)
//     * @param head
//     * @param val
//     * @return
//     */
//    public static ListNode removeElements(ListNode head, int val) {
//        if(head == null) return null;
//        head.next = removeElements(head.next, val);
//        return head.val == val?head.next:head;
//    }

    /**
     * 时间复杂度为O(n)
     * 空间复杂度为O(n)
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
//        while(head.val == val) {
        while(head != null && head.val == val) { // 缺失条件head != null
            head = head.next;
        }
        if(head == null) return null;
        ListNode cur = head;
        while (cur.next != null) {
            if(cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        int[] array = {1,2,6,3,4,5,6};

        ListNode cur = head;

        for(int i=0; i<array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            cur.next = temp;
            temp.next = null;

            cur = temp;
        }
        int val = 6;
        head = removeElements(head.next, val);

        cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
