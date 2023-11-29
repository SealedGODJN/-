package com.NPU.上海出差.模拟事件仿真.队列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 初始t0
        long t0 = System.currentTimeMillis();

        Channel channel = new Channel();

        Port a653_send = new Port(true, channel, 200);
        Port a653_recv = new Port(false, channel, 50);

//        Thread t1 = new Thread(a653_send);
//        t1.start();
//
//        Thread t2 = new Thread(a653_recv);
//        t2.start();

        List<Port> allPort = new ArrayList<>();
        allPort.add(a653_send);
        allPort.add(a653_recv);

        ExecutorService e = Executors.newFixedThreadPool(10);
        for (int i = 0; i < allPort.size(); i++) {
            System.out.println("添加端口");
            e.execute(allPort.get(i));
        }
        Thread.sleep(500);

        // 被中断的任务列表
        List<Runnable> runnableList = e.shutdownNow();
    }
}

class Port implements Runnable {
    /**
     * 端口通过line连接到另一个端口
     */
    private Channel line;

    /**
     * 最终发送端
     * 只能发送消息
     */
    private boolean isOnlySend;

    /**
     * 生产消息的频率
     */
    private long refreshPeriod;

    public Port(boolean isOnlySend, Channel line, long refreshPeriod) {
        this.isOnlySend = isOnlySend;
        this.line = line;
        this.refreshPeriod = refreshPeriod;
    }

    public void generateFlow() throws InterruptedException {
        int i = 0;
        while (i < 10) {

            Flow f1 = new Flow();
            f1.id = "flow" + i;
            i++;
            f1.generateTime = System.currentTimeMillis();
            f1.totalDelay = 0;
            f1.origin = "A653";
            f1.dest = "A653";

            line.addAfterDelay(f1, refreshPeriod);
        }
        System.out.println();
    }

    public void recvFlow() throws InterruptedException {
        while (true) {
            line.handleMessage(refreshPeriod);
        }
    }

    @Override
    public void run() {
        if (isOnlySend) {
            try {
                generateFlow();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "被中断了");
            }
        } else {
            try {
                System.out.println("开始接收消息");
                recvFlow();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "被中断了");
            }
        }
    }
}

// 队列
// 之后要换为O(1)的队列

/**
 * 连接两个网络节点的channel
 */
class Channel {
    /**
     * 队列长度
     */
    private int queueSize;

    /**
     * 存储结构
     */
    private LinkedList<Flow> storage;

    public Channel() {
        queueSize = 10;
        storage = new LinkedList<>();
    }

    /**
     * @param message >0时要sleep
     * @param delay
     * @throws InterruptedException
     */
    public synchronized void addAfterDelay(Flow message, long delay) throws InterruptedException {
        // 如果满了，则一直等待
        while (!Thread.currentThread().isInterrupted() && storage.size() == queueSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                // 线程在wait或sleep期间被中断了
//                e.printStackTrace();

                Thread.currentThread().interrupt();//重新设置中断标示
            } finally {
                //线程结束前做一些清理工作
                System.out.println(Thread.currentThread().getName() + "在生产者中" + "被中断了");
            }
        }

        if (delay > 0) {
//            Thread.sleep(delay);
            message.totalDelay += delay;
        }
        // 延迟一段时间再加入队列
        storage.add(message);
        System.out.println("发送消息: " + message.id);

        // 呼唤消费者，可以消费
        notify();
    }

    /**
     * @param delay >0时要sleep
     * @throws InterruptedException
     */
    public synchronized void handleMessage(long delay) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted() && storage.size() == 0) {
            try {
                // 阻塞，等待生产者消费put()
                wait();
            } catch (InterruptedException e) {
                // 线程在wait或sleep期间被中断了
//                e.printStackTrace();

                Thread.currentThread().interrupt();//重新设置中断标示
            } finally {
                //线程结束前做一些清理工作
                System.out.println(Thread.currentThread().getName() + "在消费者中" + "被中断了");
            }
        }

        if (storage.isEmpty()) return;
        // 延迟一段时间再取出队列
        Flow flow = storage.removeFirst();
        if (delay > 0) {
//            Thread.sleep(delay);
            flow.totalDelay += delay;
            flow.totalDelay += (System.currentTimeMillis() - flow.generateTime);
        }
        System.out.println("totalDelay=" + flow.totalDelay);

        // 通知生产者继续干活
        notify();
    }
}
