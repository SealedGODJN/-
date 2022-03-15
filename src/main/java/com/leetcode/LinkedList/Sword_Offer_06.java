package com.leetcode.LinkedList;

import java.util.ArrayList;
import java.util.Stack;

public class Sword_Offer_06 {
    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) { this.val = val; }
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) return new ArrayList<>();
        ListNode temp = listNode;
        Stack<Integer> record = new Stack<>();
        while (temp != null) {
            record.push(temp.val);
            temp = temp.next;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!record.isEmpty()) {
            result.add(record.pop());
        }
        return result;
    }
}
