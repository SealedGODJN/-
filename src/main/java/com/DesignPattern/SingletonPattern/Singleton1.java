package com.DesignPattern.SingletonPattern;

/**
 * 在类加载时候就创建了实例，属于饿汉模式。其是线程安全的，这一点由JVM来保证.
 *
 * 缺点，可以通过反射创建新的实例。
 *
 * 如果让你改进，你怎么弄呢？
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
