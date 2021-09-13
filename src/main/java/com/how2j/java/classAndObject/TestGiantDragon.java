package com.how2j.java.classAndObject;

public class TestGiantDragon {
    public static void main(String[] args) {
        // 报错：构造函数是private
        // GiantDragon g = new GiantDragon();
        GiantDragon g1 = GiantDragon.getInstance();
        GiantDragon g2 = GiantDragon.getInstance();
        GiantDragon g3 = GiantDragon.getInstance();

        System.out.println(g1==g2);
        System.out.println(g3==g2);
    }
}
