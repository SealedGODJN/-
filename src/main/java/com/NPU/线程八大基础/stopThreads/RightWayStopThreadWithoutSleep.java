package com.NPU.线程八大基础.stopThreads;

/**
 * @description run方法内灭有sleep或wait方法时，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadWithoutSleep());
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        // 循环中判断是否中断
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }
}
