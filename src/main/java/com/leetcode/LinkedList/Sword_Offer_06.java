package com.leetcode.LinkedList;

import com.leetcode.LinkedList.双指针.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Sword_Offer_06 {

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

//    ArrayList<Integer> result = new ArrayList<>();
//    /**
//     *
//     * @param listNode
//     * @return
//     */
//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//
//        if (listNode != null) {
//            printListFromTailToHead(listNode.next);
//            result.add(listNode.val);
//        }
//        return result;
//    }

    List<Integer> result = new ArrayList<>();

    /**
     * 1、方法头+返回值+参数
     * 已确定
     * <p>
     * 2、终止条件
     * 未终止
     * <p>
     * 3、单层逻辑
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return result.stream().mapToInt(Integer::valueOf).toArray();
        }
        reversePrint(head.next);
        // 后序遍历（可实现倒序）
        result.add(head.val);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        int[] array = {4, 6};
        ListNode.createListNode(head, array);

        Sword_Offer_06 swordOffer06 = new Sword_Offer_06();
        int[] result = swordOffer06.reversePrint(head);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
