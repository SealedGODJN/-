package com.leetcode;

import java.util.HashMap;
import java.util.List;

public class LinkedLIst_141 {
    /**
     * 时间复杂度为O(n)
     * @param head
     * @return
     */
//    public static boolean hasCycle(ListNode head) {
//        if(head == null) return false;
//
//        HashMap<ListNode, Boolean> record = new HashMap<ListNode, Boolean>();
//
//        ListNode cur = head;
//
//        while(cur.next != null) {
//            if(record.getOrDefault(cur, false)) return true;
//            else { // 没有再hashmap中找到该节点，则现在将该节点插入
//                record.put(cur, true);
//            }
//            cur = cur.next;
//        }
//        return false;
//    }

    /**
     * 时间复杂度为O(n)
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        int length = 0;
        while (slow.next != null) {
            slow = slow.next;
            length++;
//            if (length>10000) return true; // 快慢指针一定会相遇

            if(fast.next != null) {
                if (fast.next.next != null)
                    fast = fast.next.next; // fast.next属于单数个指针，虽然fast是跳着走，但是在逻辑上会经过所有节点
                else return false;
            }
            else return false;

            if(fast.equals(slow)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        int[] array = { 2};

        ListNode cur = head;

        for(int i=0; i<array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            cur.next = temp;
            temp.next = null;

            cur = temp;
        }

//        cur.next = head.next; // 形成环


        boolean result = hasCycle(head);
        System.out.println(result);
    }
}
