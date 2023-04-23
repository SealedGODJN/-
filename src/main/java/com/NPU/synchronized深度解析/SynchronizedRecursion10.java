package com.NPU.synchronized深度解析;

/**
 * 可重入锁
 *
 * @description 递归调用本方法（可重入锁：递归调用自己是成功的）
 */
public class SynchronizedRecursion10 {
    int a = 0;

    public synchronized void method1() {
        System.out.println("这是method1，a = " + a);
        if (a == 0) {
            a++;
            method1();
        }
    }

    public static void main(String[] args) {
        SynchronizedRecursion10 s = new SynchronizedRecursion10();
        s.method1();
    }
}
