package com.leetcode;

import java.util.HashMap;

public class LinkedList_142 {
    /**
     * 哈希表法，遍历一遍链表
     * 时间复杂度为O(n)
     * 空间复杂度O(n)
     *
     * @param head
     * @return
     */
//    public static ListNode detectCycle(ListNode head) {
//        if(head == null) return null;
//
//        HashMap<ListNode, Boolean> record = new HashMap<ListNode, Boolean>();
//
//        ListNode cur = head;
//
//        while(cur.next != null) {
//            if(record.getOrDefault(cur, false)) return cur;
//            else { // 没有再hashmap中找到该节点，则现在将该节点插入
//                record.put(cur, true);
//            }
//            cur = cur.next;
//        }
//        return null;
//    }

    /**
     * 快慢指针法，数学推导，得到
     * 2(x+y) = x+y+n(y+z)
     * x=n(y+z)-y
     * x=(n-1)(y+)+z
     * n=1 时，x=z，即fast和slow第一次相遇时，相遇指针继续一个一个往前走，然后让一个指针从头节点出发，也是一个一个往前走，则需要走x=z步
     * 它们即可相遇
     *
     * 时间复杂度为O(n)
     * 空间复杂度O(1)
     *
     * @param head 链表的表头
     * @return 如果存在环，则返回环的起点，否则返回空
     */
//    public static ListNode detectCycle(ListNode head) {
//        if(head == null) return null;
//
//        ListNode fast = head;
//        ListNode slow = head;
//        while (slow.next != null) {
//            slow = slow.next;
//            if (fast.next != null) {
//                if (fast.next.next != null)
//                    fast = fast.next.next;
//                else return null;
//            }
//            else return null;
//            if (fast == slow) {
//                break;
//            }
//        }
//        fast = head;
//        while (slow.next != null) {
//            if(fast == slow) return slow;
//
//            slow = slow.next;
//            fast = fast.next;
//        }
//        return null;
//    }

    /*
解释一下为什么慢指针入环第一圈没走完的时候就会和快指针相遇

设 环的长度为A,慢指针在入环的时候快指针在环中的位置B(取值范围0到A-1),
当快慢指针相遇时 [慢指针在环中走了C] ,有
    C % A = ( B + 2C) % A,等价于
    A*n + C = B + 2C,合并得
    C = A*n - B
    当 n=1 时 , 0 <= C < A
故 慢指针在第一圈必定能和快指针相遇
     */

    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null) {
            slow = slow.next;

            if (fast.next != null) {
                if (fast.next.next != null) {
                    fast = fast.next.next;
                } else return null;
            } else return null;

            if (fast == slow) break;
        }
        fast = head;
        while (slow.next != null) {
            if (slow == fast) return slow; // 应该放到第一句
            slow = slow.next;
            fast = fast.next;

        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        int[] array = {2};
//        int[] array = {2, 3, 4, 5, 6};

        ListNode cur = head;

        for(int i=0; i<array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            temp.next = null;
            cur.next = temp;
            cur = temp;
        }

        cur.next = head; // 形成环

        ListNode result = detectCycle(head);
        assert result != null;
        System.out.println(result.val);
    }
}
