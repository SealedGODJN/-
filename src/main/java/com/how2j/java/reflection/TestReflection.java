package com.how2j.java.reflection;

import com.how2j.bean.Hero;
import com.how2j.java.interfaceAndExtend.Test;

public class TestReflection {
    public static void method1() {
        synchronized (TestReflection.class) {
            // 对于method1而言，同步对象是TestReflection.class，
            // 只有占用了TestReflection.class才可以执行到这里
            System.out.println(Thread.currentThread().getName() + " 进入了method1方法");
            try{
                System.out.println("运行5秒");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "进入了method2");
        try{
            System.out.println("运行5秒");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        String className = "com.how2j.bean.Hero";
//        try {
//            // 以下三种都是classloader新建的类对象
//            Class pClass2 = Hero.class; // 这种方式不会导致静态属性被初始化
//            System.out.println(Hero.getCopyright());
//
//
//            /**
//             * 获取类对象的时候，会导致类属性被初始化
//             */
//            Class pClass1 = Class.forName(className);
//            System.out.println(Hero.getCopyright());
//
//
//            Class pClass3 = new Hero().getClass();
//            System.out.println(Hero.getCopyright());
//
//
//            System.out.print("pClass1 == pClass2:");
//            System.out.println(pClass1 == pClass2);
//
//            System.out.print("pClass1 == pClass3:");
//            System.out.println(pClass1 == pClass3);
//
//            System.out.print("pClass2 == pClass3:");
//            System.out.println(pClass2 == pClass3);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        Thread t1 = new Thread(){
            public void run() {
                // 调用method1
                TestReflection.method1();
            }
        };
        t1.setName("第一个线程");
        t1.start();

        // 保证第一个线程先调用method1
        Thread.sleep(1000);

        Thread t2 = new Thread() {
            @Override
            public void run() {
                TestReflection.method2();
            }
        };
        t2.setName("第二个线程");
        t2.start();
    }
}
