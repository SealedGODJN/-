package com.NPU.java内存模型.重排序;

import java.util.concurrent.CountDownLatch;

/**
 * @description 演示重排序的现象
 * 直到某个条件才停止，测试小概率事件
 */
public class OutOfOrderExecution1 {
    private static volatile int x = 0, y = 0;
    private static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            // 值的初始化
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    b = 1;
                    y = a;
                }
            });

            one.start();
            two.start();

            latch.countDown();

            one.join();
            two.join();

            String result = "第" + i + "次 （" + x + "," + y + ")";

            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
