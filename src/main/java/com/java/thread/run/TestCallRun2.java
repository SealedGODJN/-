package com.java.thread.run;

/**
 * 直接调用run
 *
 * 而不是start
 */
class TestCallRun2 extends Thread {
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        TestCallRun2 t1 = new TestCallRun2();
        TestCallRun2 t2 = new TestCallRun2();

        t1.run();
        t2.run();

        /*
1
2
3
4
1
2
3
4
        分析：
        1、输出结果是t1顺序执行完，再由t2顺序执行完。而不是两个线程交替执行：t1执行一次（sleep），然后再t2执行一次（sleep）
        2、程序执行并没有上下文切换，因为这里t1和t2将被视为普通对象而不是线程对象。
         */
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/what-if-we-call-run-method-directly.html