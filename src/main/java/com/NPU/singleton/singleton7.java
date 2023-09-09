package com.NPU.singleton;

/**
 * @description 静态内部类（可用）
 */
public class singleton7 {
    private singleton7() {

    }

    public static singleton7 getInStance() {
        // 懒汉式
        return SingleInstance.INSTANCE;
    }

    private static class SingleInstance {
        private static final singleton7 INSTANCE = new singleton7();
    }
}
