package com.NPU.面向对象设计.chapter6;

import com.NPU.面向对象设计.chapter4.Point2D;

import java.util.*;

public class Test {
    public static void main(String[] args) {
// // 示例6.10 容器同步修改异常示例
//        ArrayList<String>  arrayList = new ArrayList<String>();
//        arrayList.add("Vectors");
//        arrayList.add(" and ");
//        arrayList.add("Iterators");
//
//        String result = "";
//        Iterator<String>  iterator = arrayList.iterator();
//        while (iterator.hasNext()) {
//            result =  iterator.next();
//            iterator.remove();
//            arrayList.add("cat");//不允许，会抛出异常//java.util.ConcurrentModificationException
//        }
//        System.out.println(result);

//        示例6.11 For-each循环的语法
        Collection<Point2D> c = new ArrayList<>();
        for (Point2D point : c) {
            float x = point.getX();//对于容器 c中存储的每一个元素point，作如下处理……
            //……
        }
    }
}
