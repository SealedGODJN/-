package com.java.thread.Multitasking;

/**
 * 使用扩展Thread类的匿名类
 */
public class TestMultitasking4 {
    public static void main(String args[]) {
        Thread t1 = new Thread() {
            public void run() {
                System.out.println("task one");
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                System.out.println("task two");
            }
        };

        t1.start();
        t2.start();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/multitasking-in-multithreading.html

