package com.java.thread;

public class example2 {
    public static class MyThread implements Runnable { // 2、实现Runnable接口（java中创建线程的三种方式）
        @Override
        public void run() {
            System.out.println("new Thread");
        }
    }

    public static void main(String[] args) {
        new MyThread().run();

        // 函数式编程
        new Thread(()->{
           System.out.println("java 8 匿名类"); // 3、匿名类（java中创建线程的三种方式）
        }).start();
    }
}
