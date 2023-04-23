package com.NPU.synchronized深度解析;

/**
 * @description 调用类内的另外方法（成功）
 */
public class SynchronizedOtherMethod11 {

    public synchronized void method1() {
        System.out.println("这是method1");
        // 进入method2之前，已经拿到synchronized这把锁（锁this）
        method2();
    }

    private synchronized void method2() {
        System.out.println("这是method2");
    }

    public static void main(String[] args) {
        SynchronizedOtherMethod11 s = new SynchronizedOtherMethod11();
        s.method1();
    }
}
