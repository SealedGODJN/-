package com.NPU.singleton;

/**
 * @description 懒汉式（线程不安全）
 */
public class singleton3 {
    private static singleton3 INSTANCE = null;

    private singleton3() {

    }

    public static singleton3 getInStance() {
        // 如果多线程同时调用getInStance()，会同时创建多个INSTANCE实例
        if (INSTANCE == null) {
            INSTANCE = new singleton3();
        }
        return INSTANCE;
    }
}
