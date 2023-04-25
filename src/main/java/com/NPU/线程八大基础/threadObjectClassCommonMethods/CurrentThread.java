package com.NPU.线程八大基础.threadObjectClassCommonMethods;

public class CurrentThread implements Runnable {

    public static void main(String[] args) {
        new CurrentThread().run();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
