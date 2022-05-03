package com.NPU.面向对象设计.chapter6;

import java.util.*;

public class ArrayListExample {

    public static void main(String args[]) {

        ArrayList<String> a = new ArrayList<String>();

        a.add(new String("xiao1"));
        a.add(new String("xiao2"));
        a.add(new String("xiao3"));

        System.out.println("======普通遍历======");
        /*遍历容器*/
        for (int j = 0; j < a.size(); j++) {
            String str = a.get(j);
            System.out.println(str);
        }

        System.out.println("======iterator======");
        /*遍历并访问容器中的元素*/
        Iterator<String> e = a.iterator();

        while (e.hasNext()) {
            String str = e.next();
            System.out.println(str);
        }

        // 示例6.12 ArrayList容器遍历方法之三
        System.out.println("======for-each======");
        /*遍历容器*/
        for (String str : a) {
            System.out.println(str);
        }
    }
}