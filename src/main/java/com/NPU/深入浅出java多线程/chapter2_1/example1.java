package com.NPU.深入浅出java多线程.chapter2_1;


public class example1 {
    /**
     * <p>
     * java的线程调度程序是JVM的一部分，它决定应该运行哪个线程。无法保证线程调度程序将选择运行哪个可运行线程。<br>
     *  一次只能有一个线程在一个进程中运行。线程调度程序主要使用抢占式或时间切片调度来调度线程。<br>
     * </p>
     * <br>
     * <br>
     *  //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：
     *  https://www.yiibai.com/java_multithreading/thread-scheduler-in-java.html#article-start
     */
    public static class MyThread extends Thread { // 1、继承线程类（创建线程有3种方式）
        @Override
        public void run() {
            System.out.println("new thread");
        }
    }

    public static void main(String[] args) {
        Thread mythread = new MyThread(); // 在静态方法中new一个内部类，需要设定该类为static
        mythread.start();

        Thread t1 = new Thread();
        t1.start();
    }
}
