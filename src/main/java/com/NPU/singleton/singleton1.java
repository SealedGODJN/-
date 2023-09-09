package com.NPU.singleton;

/**
 * @description 饿汉式（静态常量）
 */
public class singleton1 {
    private final static singleton1 INSTANCE = new singleton1();

    private singleton1() {

    }

    public static singleton1 getInStance() {
        return INSTANCE;
    }
}
