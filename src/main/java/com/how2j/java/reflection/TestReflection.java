package com.how2j.java.reflection;

import com.how2j.bean.Hero;

public class TestReflection {
    public static void main(String[] args) {
        String className = "com.how2j.bean.Hero";
        try {
            // 以下三种都是classloader新建的类对象

            Class pClass1 = Class.forName(className);
            Class pClass2 = Hero.class; // 这种方式不会导致静态属性被初始化
            Class pClass3 = new Hero().getClass();
            System.out.print("pClass1 == pClass2:");
            System.out.println(pClass1 == pClass2);

            System.out.print("pClass1 == pClass3:");
            System.out.println(pClass1 == pClass3);

            System.out.print("pClass2 == pClass3:");
            System.out.println(pClass2 == pClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
