package com.java.review.thread;

public class example1 {
    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("new thread");
        }
    }

    public static void main(String[] args) {
        Thread mythread = new MyThread(); // 在静态方法中new一个内部类，需要设定该类为static
        mythread.start();
    }
}
