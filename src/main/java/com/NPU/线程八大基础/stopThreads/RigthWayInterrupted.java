package com.NPU.线程八大基础.stopThreads;

/**
 * @description 分辨Thread.interrupted()、非static interrupted()和interrupt()
 */
public class RigthWayInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("第一次中断: " + Thread.currentThread().isInterrupted());
                        System.out.println("第一次响应中断后: " + Thread.currentThread().getState());
                        break;
                    }
                }
                for (; ; ) {
                    // 获取中断标志并重置
//                    System.out.println("第二轮：threadOne-Thread.isInterrupted: " + Thread.interrupted());
                    System.out.println("第二轮：" + Thread.currentThread().getState());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("响应中断：" + Thread.currentThread().getState());
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // 启动线程
        threadOne.start();
        // 设置中断标志
        threadOne.interrupt();
        // 获取中断标志
        System.out.println("threadOne-isInterrupted: " + threadOne.isInterrupted());
        // 获取中断标志并重置
        System.out.println("main-isInterrupted: " + threadOne.interrupted());
        // 获取中断标志并重置
        System.out.println("main-isInterrupted: " + Thread.interrupted());
        // 获取中断标志
        System.out.println("threadOne-isInterrupted: " + threadOne.isInterrupted());

        System.out.println("join之前：" + threadOne.getState());
        threadOne.join();
        System.out.println("join后：" + threadOne.getState());

        System.out.println("Main thread is over.");

        System.out.println("最后：" + threadOne.getState());
    }
}
