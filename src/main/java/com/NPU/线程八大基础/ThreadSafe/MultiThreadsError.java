package com.NPU.线程八大基础.ThreadSafe;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description 第一种：运算结果出错
 * 演示计数不准确（减少），找出具体出错的位置
 */
public class MultiThreadsError implements Runnable {

    static MultiThreadsError instance = new MultiThreadsError();
    // 加了volatile，计算结果仍然不正确
    int index = 0;
    //    String pre = "";
//    String cur = "";
//    int preI = 0;
//    static int count = 0;
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    final boolean[] marked = new boolean[1000000];
    // 让两个线程都在同一个代码处等待，当两个线程都就绪之后，再继续执行
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("表面上结果是" + instance.index);
        System.out.println("真正运行的次数" + realIndex.get());
        System.out.println("错误次数" + wrongCount.get());
    }

    @Override
    public void run() {
//        while (index < 10000) {
//            index++;
//        }
        for (int i = 0; i < 10000; i++) {
            // 方法0【错误】：认为线程1访问index之后，标记上一个访问的线程为pre，下一次访问index前检查当前访问的线程cur是否和上一次访问的线程pre相同
//            cur = Thread.currentThread().getName();

            // 当两个线程都执行过await()之后，才会让代码继续执行
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            index++;
            // 让两个线程都完成index++之后，就不会出现一个线程进入后方synchronized代码块中，而index被另一个进程修改了
            // 当两个线程都执行过await()之后，才会让代码继续执行
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            realIndex.incrementAndGet();
            // 方法1：
            synchronized (instance) {
                // 错误一：两个线程同时访问到同一个index，同时++，反而不会触发“发生错误”
                // 错误二：两个线程竞争instance这把锁，一个线程先进入，完成对index=1对应的marked[1]赋值为true，退出后，回到循环的开始，先于第二个线程完成了index++;
                // 第二个线程刚刚进入synchronized代码块，而index已经从1变为2，此时marked[2]=false，不触发错误
                if (marked[index] && marked[index - 1]) {
                    // 错误3：由于synchronized是保证变量可见性的，因此，每一次线程1修改完index，线程2拿到的index都是++后的结果
                    System.out.println("发生错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }


//            // 方法2：默认为false，marked[index]被访问两次之后，则仍然是false
//            marked[index] = !marked[index];
//            if (!pre.equals("") && !pre.equals(cur) && preI != i) {
//                System.out.println("上次一访问和这一次访问的index不同！" + ++count);
//            }
//            pre = cur;
//            preI = i;
        }
    }
}
