package com.java.review.huawei;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3_1 {
    public static void main(String orgs[]) {
        final MyService1 start = new MyService1();
        new Thread(new Runnable() {
            public void run() {
                start.service("10");
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                start.service("1200");
            }
        }).start();
    }
}

class MyService1 {
    private Lock lock = new ReentrantLock();
    public void service(Object inputData) {
        lock.lock();
        try {
            Thread.sleep(100);
            int data = Integer.parseInt((String) inputData);
            int result = data - 100;
            System.out.println("取款100,取款后余额为： " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { // 如果不加finally，服务了第一个用户之后，无法服务第二个用户
            lock.unlock();
        }
    }
}