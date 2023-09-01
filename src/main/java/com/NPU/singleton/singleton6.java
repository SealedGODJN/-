package com.NPU.singleton;

/**
 * @description 双重检查（推荐面试使用）
 */
public class singleton6 {
    private volatile static singleton6 INSTANCE = null;

    private singleton6() {

    }

    public static singleton6 getInStance() {
        // 如果多线程同时调用getInStance()，会同时创建多个INSTANCE实例

        if (INSTANCE == null) {
            // 一旦两个线程都进入到if语句中，INSTANCE会被initialize两次
            synchronized (singleton6.class) {
                // 双重检查
                // 保证单例
                if (INSTANCE == null) {
                    INSTANCE = new singleton6();
                }
            }
        }

        return INSTANCE;
    }
}
