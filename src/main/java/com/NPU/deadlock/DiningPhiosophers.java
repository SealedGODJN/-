package com.NPU.deadlock;

import com.bugstack小傅哥.hashcode_2.TestHashMap;

/**
 * @description 演示哲学家就餐问题导致的死锁
 */
public class DiningPhiosophers {

    private static int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        Object[] chopstick = new Object[NUM_PHILOSOPHERS];
        for (int i = 0; i < philosophers.length; i++) {
            chopstick[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            // 改变最后一个哲学家拿筷子的顺序
            if (i == philosophers.length - 1) {
                // 防止造成 循环依赖
                philosophers[i] = new Philosopher(chopstick[(i + 1) % philosophers.length], chopstick[i]);
            } else {
                philosophers[i] = new Philosopher(chopstick[i], chopstick[(i + 1) % philosophers.length]);
            }
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }

    public static class Philosopher implements Runnable {

        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Pick up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstick--->eat");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long) (Math.random() * 10));
        }
    }
}
