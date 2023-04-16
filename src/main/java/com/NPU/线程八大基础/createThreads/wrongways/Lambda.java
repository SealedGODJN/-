package com.NPU.线程八大基础.createThreads.wrongways;

/**
 * @description 仅仅是改变了写法，仍然是Thread
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}