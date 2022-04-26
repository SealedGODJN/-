package com.NPU.面向对象设计.chapter6;

import java.util.*;

public class ArrayListExample {

    public static void main(String args[]) {

        ArrayList<String> a = new ArrayList<String>();

        a.add(new String("xiao1"));
        a.add(new String("xiao2"));
        a.add(new String("xiao3"));

        /*遍历容器*/
        for (int j = 0; j < a.size(); j++) {
            String str = a.get(j);
            System.out.println(str);
        }
    }
}