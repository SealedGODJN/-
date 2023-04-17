package com.NPU.线程八大基础.stopThreads;

/**
 * @description 如果在执行过程中，每次循环都会调用sleep或wait等方法，try/catch语句放在循环内部
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            // 循环中判断是否中断
            try {
//                while (!Thread.currentThread().isInterrupted() && num <= 10000000) {
                while (num <= 10000000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务运行结束了");
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(5000);
        t.interrupt();
    }
}
