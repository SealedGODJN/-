package com.java.thread.priority;

class TestMultiPriority1 extends Thread {
    public void run() {
        System.out.println("running thread name is:" + Thread.currentThread().getName());
        System.out.println("running thread priority is:" + Thread.currentThread().getPriority());

    }

    public static void main(String args[]) {
        TestMultiPriority1 m1 = new TestMultiPriority1();
        TestMultiPriority1 m2 = new TestMultiPriority1();
        m1.setPriority(Thread.MIN_PRIORITY);
        m2.setPriority(Thread.MAX_PRIORITY);
        m1.start();
        m2.start();

    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/priority-of-a-thread.html#article-start

