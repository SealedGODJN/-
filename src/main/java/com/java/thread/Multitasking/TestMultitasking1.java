package com.java.thread.Multitasking;

/**
 * 如果必须由多个线程执行单个任务，则只有通过run()方法
 */
class TestMultitasking1 extends Thread {
    public void run() {
        System.out.println("task one");
    }

    public static void main(String args[]) {
        TestMultitasking1 t1 = new TestMultitasking1();
        TestMultitasking1 t2 = new TestMultitasking1();
        TestMultitasking1 t3 = new TestMultitasking1();

        t1.start();
        t2.start();
        t3.start();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/multitasking-in-multithreading.html

