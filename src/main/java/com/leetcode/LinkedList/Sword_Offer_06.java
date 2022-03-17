package com.leetcode.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Sword_Offer_06 {
    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) { this.val = val; }
    }

//    /**
//     * 非递归
//     * @param listNode
//     * @return
//     */
//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        if (listNode == null) return new ArrayList<>();
//        ListNode temp = listNode;
//        ArrayList<Integer> result = new ArrayList<>();
//        while (temp != null) {
//            result.add(0, temp.val);
//            temp = temp.next;
//        }
//        return result;
//    }

    ArrayList<Integer> result = new ArrayList<>();
    /**
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            result.add(listNode.val);
        }
        return result;
    }

}
