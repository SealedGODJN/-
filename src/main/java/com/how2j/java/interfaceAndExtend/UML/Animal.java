package com.how2j.java.interfaceAndExtend.UML;

public abstract class Animal {
    protected int legs; // 动物腿的数量
    protected Animal(int legs) {
        this.legs = legs;
    }

    public abstract void eat();

    public void walk() {
        System.out.println("Animal用"+this.legs+"条腿走路");
    }
}
