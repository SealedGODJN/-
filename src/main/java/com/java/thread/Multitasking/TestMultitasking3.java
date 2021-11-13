package com.java.thread.Multitasking;

class Simple1 extends Thread {
    public void run() {
        System.out.println("task one");
    }
}

class Simple2 extends Thread {
    public void run() {
        System.out.println("task two");
    }
}

/**
 * 如果想要通过多个线程执行多个任务，请使用多个run()方法?
 *
 * 多个Thread
 */
public class TestMultitasking3 {
    public static void main(String args[]) {
        Simple1 t1 = new Simple1();
        Simple2 t2 = new Simple2();

        t1.start();
        t2.start();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/multitasking-in-multithreading.html


