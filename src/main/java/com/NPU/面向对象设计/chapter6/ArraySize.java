package com.NPU.面向对象设计.chapter6;

import com.NPU.面向对象设计.chapter4.Point2D;

public class ArraySize {
    public static void main(String[] args) {
        //对象数组，数组变量声明时对数组变量和数组内容同时初始化
        Point2D[] a = new Point2D[]{
                new Point2D(100, 800), new Point2D(300, 400)
        };
        //对象数组变量b,c的初始化，数组元素内容为null
        Point2D[] b = new Point2D[5];
        Point2D[] c = new Point2D[4];
        //为对象数组c的元素赋值
        for (int i = 0; i < c.length; i++) {
            c[i] = new Point2D(i, i);
        }
        // 基本数据类型数组e的声明，e是空引用
        int[] e;
        //基本数据类型数组f的声明和初始化，数组元素内容为0
        int[] f = new int[5];
        //基本数据类型数组e及其元素内容的初始化，数组元素内容分别为1和2
        e = new int[]{1, 2};
        //为基本数据类型数组f的元素内容初始化
        for (int i = 0; i < f.length; i++) {
            f[i] = i * i;
        }
        //基本数据类型数组g及其元素内容的初始化，数组元素内容分别为11、47和93
        int[] g = {11, 47, 93};
        //由于b和c的数据类型一致，所以可以相互赋值
        b = c;
        //由于f和g的数据类型一致，所以可以相互赋值
        f = g;
        System.out.println("a.length = " + a.length);
        System.out.println("b.length = " + b.length);
        System.out.println("c.length = " + c.length);
        System.out.println("e.length = " + e.length);
        System.out.println("f.length = " + f.length);
        System.out.println("g.length = " + g.length);
    }
}