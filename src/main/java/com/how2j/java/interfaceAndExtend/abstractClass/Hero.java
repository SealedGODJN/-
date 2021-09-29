package com.how2j.java.interfaceAndExtend.abstractClass;

public abstract class Hero {
    String name;

    float hp;

    float armor;

    int moveSpeed;

    // 抽象方法attack
    // Hero的子类会被要求实现attack方法
    public abstract void attack();
}