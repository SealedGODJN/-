package com.how2j.java.interfaceAndExtend.Final;

/**
 * 不能够被继承
 */
public /*final*/ class Hero extends Object{

    // final 修饰类变量，代表该类变量是常量
    public static final int itemTotalNumber = 6;//物品栏的数量

    String name;
    float hp;

    public /*final*/ void useItem() {
        System.out.println("Hero use Item");
    }

    public static void main(String[] args) {
        final int hp;
        hp = 5;
//        hp = 6; // final修饰变量，该变量只有一次赋值机会

        final Hero h;
        h = new Hero();
//        h = new Hero(); // final修饰引用，引用只有1次指向对象的机会

        h.hp = 5;
    }
}
