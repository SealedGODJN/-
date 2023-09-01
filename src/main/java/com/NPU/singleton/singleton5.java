package com.NPU.singleton;

/**
 * @description 懒汉式（线程不安全）
 */
public class singleton5 {
    private static singleton5 INSTANCE = null;

    private singleton5() {

    }

    public static singleton5 getInStance() {
        // 如果多线程同时调用getInStance()，会同时创建多个INSTANCE实例
        if (INSTANCE == null) {
            // 一旦两个线程都进入到if语句中，INSTANCE会被initialize两次
            // 线程不安全！
            synchronized (singleton5.class) {
                INSTANCE = new singleton5();
            }
        }
        return INSTANCE;
    }
}
