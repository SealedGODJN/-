package com.java.concurrent.ConcurrentBook.Chapter3;

/**
 * Java默认的线程优先级为5，线程的执行顺序由调度程序来决定，线程的优先级会在线程被调用之前设定。
 * <p>
 * 通常情况下，高优先级的线程将会比低优先级的线程有更高的几率得到执行。我们使用方法Thread类的setPriority()实例方法来设定线程的优先级。
 */
public class Demo_3_2_ThreadPriority {
    public static void main(String[] args) {
        Thread a = new Thread();
        System.out.println("我是默认线程优先级：" + a.getPriority());
        Thread b = new Thread();
        b.setPriority(10);
        System.out.println("我是设置过的线程优先级：" + b.getPriority());
    }
}
