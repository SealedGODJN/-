package com.java.review.concurrent;

import sun.java2d.loops.GraphicsPrimitive;

public class DemoThread02 {
    private /*static*/ int count = 0;

    // 如果add方法和count是静态的，则syn如何处理？
    public synchronized /*static*/ void add() {
        count++;
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+">count="+count);
    }

    public static void main(String[] args) {
        /**
         * Synchronied 是获得对象锁，如果作用在静态方法上，则升级为类锁
         */

        // 内部类无法访问非final对象
        /**
         * 为什么要用final修饰局部变量？
         *
         * 内部类对象的生命周期会超过局部变量的生命周期
         *
         * 局部变量的生命周期：
         *      当该方法被调用是，该方法中的局部变量在栈中被创建，当方法调用结束时，退栈，这些局部变量全部死亡
         * 内部类对象生命周期与其它类一样：
         *      自创建一个匿名内部类对象，系统为该对象分配内存，直到没有引用变量指向分配给该对象的内存，它才会死亡（被JVM垃圾回收）
         *
         * <b>可能存在的问题：成员方法已调用结束，局部变量已死亡，但匿名内部类的对象仍然活着<b/>
         *
         * 匿名内部类对象可以访问同一个方法中被定义为final类型的局部变量
         * 局部变量定义为final后，对于匿名内部类对象要访问的所偶遇final类型局部变量，这些局部变量都会被拷贝到匿名内部类对象中，
         * 成为一个数据成员。
         *
         * 即使成员方法调用结束，栈中的局部变量都死亡，但被定义为final类型的局部变量的值永远不变，因此，匿名内部类对象在局部变量死亡后，
         * 仍然可以访问final类型的局部变量
         */
        final DemoThread02 thread1 = new DemoThread02();
        final DemoThread02 thread2 = new DemoThread02();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread1.add();  // 1.同一个对象，同一把锁
            }
        }, "thread1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread1.add(); //1.同一个对象，同一把锁
//                thread2.add();
            }
        }, "thread2");

        t1.start();
        t2.start();
    }
}
