package com.NPU.线程八大基础.stopThreads;

/**
 * @description 如果while里面放try/catch，会导致中断失效
 */
public class CanInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;

            while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("任务运行结束了");
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(5000);
        t.interrupt();
    }
}
