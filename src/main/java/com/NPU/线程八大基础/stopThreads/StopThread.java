package com.NPU.线程八大基础.stopThreads;

/**
 * @description 错误的停止发放：用stop()来停止线程，会导致线程运行一半突然停止，没办法完成一个基本单位的操作（一个连队），会产生脏数据（有的连队多领取or少领取装备）
 */
public class StopThread implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new StopThread());
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 模拟接到上级命令，突然战争爆发，导致连队要立刻停止现在的事情，赶往战场
        t.stop();
    }

    @Override
    public void run() {
        // 模拟指挥军队：一共有5个联队，每个连队100人，以连队为单位，发放武器弹药，叫到号的士兵前去领取
        for (int i = 0; i < 5; i++) {
            System.out.println("连队-" + i + "-开始领取武器");
            for (int j = 0; j < 10; j++) {
                System.out.println("\t连队-" + i + "士兵-" + j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队-" + i + "-已经领取完毕");
        }
    }
}
