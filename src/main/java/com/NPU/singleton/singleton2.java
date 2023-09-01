package com.NPU.singleton;

public class singleton2 {
    private final static singleton2 INSTANCE;

    // 实例初始化过程移到了静态代码块中
    static {
        INSTANCE = new singleton2();
    }

    private singleton2() {

    }

    public static singleton2 getInStance() {
        return INSTANCE;
    }
}
