package com.java.thread.Multitasking;

/**
 * 如果必须由多个线程执行单个任务，则只有通过run()方法
 *
 *  每个线程都在一个单独的调用堆栈中运行。
 *  Stack A: main()
 *  Stack B: run()
 *  Stack C: run()
 */
class TestMultitasking2 implements Runnable {
    public void run() {
        System.out.println("task one");
    }

    public static void main(String args[]) {
        Thread t1 = new Thread(new TestMultitasking2());// passing annonymous object of TestMultitasking2 class
        Thread t2 = new Thread(new TestMultitasking2());

        t1.start();
        t2.start();

    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/multitasking-in-multithreading.html

