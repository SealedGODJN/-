package com.NPU.线程八大基础.threadObjectClassCommonMethods;

/**
 * @description 打印main, Thread-0, Thread-1
 */
public class CurrentThread implements Runnable {

    public static void main(String[] args) {
        // 打印main的名字
        new CurrentThread().run();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
