package com.java.thread.daemon;

class TestDaemonThread2 extends Thread {
    public void run() {
        System.out.println("Name: " + Thread.currentThread().getName());
        System.out.println("Daemon: " + Thread.currentThread().isDaemon());
    }

    public static void main(String[] args) {
        TestDaemonThread2 t1 = new TestDaemonThread2();
        TestDaemonThread2 t2 = new TestDaemonThread2();
        t1.start();
        t1.setDaemon(true);// will throw exception here
        // 先start再设置为守护进程，则会报错

        t2.start();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/daemon-thread.html#article-start

