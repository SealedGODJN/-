package com.NPU.上海出差.模拟事件仿真.队列;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // 初始t0
        long t0 = System.currentTimeMillis();

        Channel channel = new Channel();

        Port a653_send = new Port(true, channel, 200);
        Port a653_recv = new Port(false, channel, 50);

        Thread t1 = new Thread(a653_send);
        t1.start();

        Thread t2 = new Thread(a653_recv);
        t2.start();
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
        while (true) {

            Flow f1 = new Flow();
            f1.id = "flow" + i;
            i++;
            f1.currentDelay = 0;
            f1.totalDelay = 0;
            f1.origin = "A653";
            f1.dest = "A653";

            line.addAfterDelay(f1, f1.currentDelay);
        }
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
                throw new RuntimeException(e);
            }
        } else {
            try {
                System.out.println("开始接收消息");
                recvFlow();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        while (storage.size() == queueSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        while (storage.size() == 0) {
            try {
                // 阻塞，等待生产者消费put()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        // 延迟一段时间再取出队列
        Flow flow = storage.removeFirst();
        if (delay > 0) {
//            Thread.sleep(delay);
            flow.totalDelay += delay;
        }
        System.out.println("totalDelay=" + flow.totalDelay);

        // 通知生产者继续干活
        notify();
    }
}
