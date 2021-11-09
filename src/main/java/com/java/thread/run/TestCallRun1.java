package com.java.thread.run;

class TestCallRun1 extends Thread {
    public void run() {
        System.out.println("running...");
    }

    public static void main(String args[]) {
        TestCallRun1 t1 = new TestCallRun1();
        t1.run();// fine, but does not start a separate call stack
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/what-if-we-call-run-method-directly.html

