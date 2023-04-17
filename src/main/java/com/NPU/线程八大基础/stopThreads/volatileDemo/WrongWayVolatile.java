package com.NPU.线程八大基础.stopThreads.volatileDemo;

/**
 * @description 演示用volatile的局限：part1 看似可行
 */
public class WrongWayVolatile implements Runnable {

    /**
     * 可见性：多个线程都能看到该变量真实的值
     */
    private volatile boolean canceled = false;


    @Override
    public void run() {

    }
}
