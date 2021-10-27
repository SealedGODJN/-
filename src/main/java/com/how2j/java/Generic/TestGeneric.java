package com.how2j.java.Generic;

import java.util.ArrayList;

public class TestGeneric {
    public static void test1() {
        ArrayList heros = new ArrayList();

        heros.add(new APHero());
        heros.add(new ADHero());

        APHero apHero =  (APHero) heros.get(0);
        ADHero adHero =  (ADHero) heros.get(1);

        ADHero adHero2 =  (ADHero) heros.get(0);
        // com.how2j.java.Generic.APHero cannot be cast to com.how2j.java.Generic.ADHero
    }

    /**
     * 使用泛型的好处：
     * 泛型的用法是在容器后面添加<Type>
     * Type可以是类，抽象类，接口
     * 泛型表示这种容器，只能存放APHero，ADHero就放不进去了。
     */
    public static void test2() {
        ArrayList<APHero> heros = new ArrayList<APHero>();

        //只有APHero可以放进去
        heros.add(new APHero());

        //ADHero甚至放不进去
        //heros.add(new ADHero());

        //获取的时候也不需要进行转型，因为取出来一定是APHero
        APHero apHero =  heros.get(0);
    }

    /**
     * 根据数字类的知识，设计一个集合，这个集合里即可以放整数，也可以放浮点数，但是不能放字符串
     * Number是所有Number的子类的父类，子类有：Byte, Integer, Double, Float, Long
     */
    public static void train1() {
        ArrayList<Number> arrayList = new ArrayList<>();
        int i = 1;
        arrayList.add(i);
        arrayList.add(1.2);
        arrayList.forEach(System.out::println); // lambda
//        arrayList.add((Character)'a');
//        Character a = 'a'; // 其父类不是Number
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        train1();
    }
}
