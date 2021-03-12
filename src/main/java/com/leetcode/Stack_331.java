package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Stack_331 {
    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(isValidSerialization(preorder));
    }
    public static boolean isValidSerialization(String preorder) {
//        String[] tree = preorder.split(",");

        // 方法一失败
//        Deque<Integer> record = new LinkedList<Integer>(); // 栈判断tree是否能构成树形结构
//        record.add(1);
//        for(String i: tree) {
//            if(!i.equals("#") && record.isEmpty()) {
////            if(record.isEmpty()) {
//                record.add(2);
//            } else if(!record.isEmpty()) {
//                Integer temp = record.pop();
//                temp--;
//                if(temp < 0) return false;
//                if(temp != 0) record.add(temp);
//            }
//        }
//        if(record.isEmpty()) return true;
//        return false;
        int num = 0; // 记录“#”的个数
        for(int i = preorder.length()-1; i>=0; i--) {
            if(preorder.charAt(i) == ',') continue;
            if(preorder.charAt(i) == '#') num++;
            else if(preorder.charAt(i) != '#') num--;
            if(num > 2) return false;
        }
        if(num == 1) return true;
        else return false;
    }
}

// 问题：
//输入：
//"1,#,#,#,#"
//输出：
//true
//预期结果：
//false

// 错误原因：未对根节点进行特殊处理


//题解1
//不需要栈，用已有的string就行，时间复杂度O（n）,空间复杂度O（1），执行4ms。
//string从后遍历，用num记录#的个数，当遇到正常节点时，#的个数-2，
//并将该节点转化成#，num+1，，整体即为num-1; 当出现num的个数不足2时，即false，最终也须保证num为1。

//题解2：
//把此问题中的空节点理解成叶子节点。
//可以理解成是节点数问题，叶子节点数总是比非叶子节点数多一。
//根据前序遍历过程，先遍历的非叶子节点数总是比叶子节点数多。
//也可以理解为出度入度相等问题：我命名有问题，根结点的入度为0出度为2，
//其他非叶子结点的入度为1出度为2，叶子节点入度为1出度为0。
//因为根节点多出来一个出度，所以初始化度为1，一个非叶子节点时度+1，
//加入一个空节点（叶子节点）时度-1，如果度为0，即达到出度入度相等，已经形成一颗二叉树。