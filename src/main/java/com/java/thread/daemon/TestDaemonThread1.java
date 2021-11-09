package com.java.thread.daemon;

public class TestDaemonThread1 extends Thread {
    public void run() {
        if (Thread.currentThread().isDaemon()) {// checking for daemon thread
            System.out.println("daemon thread work");
        } else {
            System.out.println("user thread work");
        }
    }

    public static void main(String[] args) {
        TestDaemonThread1 t1 = new TestDaemonThread1();// creating thread
        TestDaemonThread1 t2 = new TestDaemonThread1();
        TestDaemonThread1 t3 = new TestDaemonThread1();

        t1.setDaemon(true);// now t1 is daemon thread

        t1.start();// starting threads
        // 如果要将用户线程设置为守护进程，则不能启动它，否则将抛出IllegalThreadStateException
        // TODO 自相矛盾?


        t2.start();
        t3.start();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/daemon-thread.html#article-start

