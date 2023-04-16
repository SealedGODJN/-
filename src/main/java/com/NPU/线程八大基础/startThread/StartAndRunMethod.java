package com.NPU.线程八大基础.startThread;

/**
 * @description 对比start和run两种启动线程的方式
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run(); // 输出 main

        new Thread(runnable).start(); // 输出 Thread-0
    }
}
