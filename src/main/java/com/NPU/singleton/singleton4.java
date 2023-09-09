package com.NPU.singleton;

/**
 * @description 懒汉式（线程安全）
 */
public class singleton4 {
    private static singleton4 INSTANCE = null;

    private singleton4() {

    }

    // 实例锁
    public synchronized static singleton4 getInStance() {
        // 如果多线程同时调用getInStance()，会同时创建多个INSTANCE实例
        if (INSTANCE == null) {
            INSTANCE = new singleton4();
        }
        return INSTANCE;
    }
}
