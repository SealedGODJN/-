package com.NPU.线程八大基础.sixstates;

/**
 * @description 展示Blocked, Waiting, TimedWaiting
 */
public class BlockedWaitingTimedWaiting implements Runnable {
    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread t2 = new Thread(runnable);
        t2.start();

        // 线程1正在sleep【TIMED_WAITING】，线程2拿不到syn()方法的锁【BLOCKED】
        System.out.println(t1.getState());
        System.out.println(t2.getState());

        try {
            // 让线程1从TIMED_WAITING变为WAITING
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(t1.getState());
    }

    @Override
    public void run() {
        // 线程1正在sleep【TIMED_WAITING】，线程2拿不到锁【BLOCKED】
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
