package com.NPU.线程八大基础.stopThreads;

/**
 * @description 如果在执行过程中，会调用sleep或wait等方法，那么？
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            // 循环中判断是否中断
            try {
                while (!Thread.currentThread().isInterrupted() && num <= 300) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务运行结束了");
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }
}
