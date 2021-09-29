package com.how2j.java.interfaceAndExtend.Super;

public class ADHero extends Hero {
    public ADHero() {
//        super(); // 注释了，也会调用父类的构造方法
        System.out.println("AD Hero的构造方法");
    }

    public ADHero(String name, float hp) {
        super(name, hp);
        System.out.println("AD Hero的有两个参数的构造方法");
    }

    public static void main(String[] args) {
        new ADHero();

        new ADHero("盖伦", 1000);
    }
}
