package com.NPU.线程八大基础.stopThreads.volatileDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @description 演示用volatile的局限part2
 * 陷入阻塞时，volatile是无法
 * <p>
 * 此例中，生产者的生产速度很快，消费者消费速度慢，出现阻塞队列满的情况。此时，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);

        // 消费者需要的时候，去消费
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦“消费者不需要更多数据了”，则我们应该让生产者也停下来
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

class Producer implements Runnable {
    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数，被放到仓库中了");
                }
                num++;
//                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}