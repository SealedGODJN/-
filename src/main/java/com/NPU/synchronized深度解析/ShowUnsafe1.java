package com.NPU.synchronized深度解析;

/**
 * @description 线程不安全：两个线程同时操作一个int i，最后i是否==200000？不是！
 */
public class ShowUnsafe1 implements Runnable {

    static ShowUnsafe1 r = new ShowUnsafe1();
    static int i = 0;

    @Override
    // 方法2
    public /*synchronized*/ void run() {
        // 方法3
        synchronized (ShowUnsafe1.class) {
//        // 方法1
//        synchronized (this) {
            for (int j = 0; j < 100000; j++) {
                // 1、读取i的值
                // 2、计算i+1
                // 3、把i+1的计算结果写回到内存中，赋给i
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
