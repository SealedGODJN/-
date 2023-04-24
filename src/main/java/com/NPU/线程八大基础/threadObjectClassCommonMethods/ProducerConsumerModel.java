package com.NPU.线程八大基础.threadObjectClassCommonMethods;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @description 用wait/notify来实现，不允许使用阻塞队列
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

/**
 * @description 代替阻塞队列
 */
class EventStorage {
    private int maxSize;

    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    /**
     * 1、未满，继续放东西<br>
     * 2、满了，则不放东西，通知等待<br>
     */
    public synchronized void put() {
        // 如果满了，则一直等待
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品");
        // 呼唤消费者，可以消费
        notify();
    }

    /**
     *
     */
    public synchronized void take() {
        while (storage.size() == 0) {
            try {
                // 阻塞，等待生产者消费put()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.poll() + "，现在仓库还剩下" + storage.size());
        // 通知生产者继续干活
        notify();
    }
}
