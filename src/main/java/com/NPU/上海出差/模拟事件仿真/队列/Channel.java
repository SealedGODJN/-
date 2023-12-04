package com.NPU.上海出差.模拟事件仿真.队列;

import lombok.Getter;

import java.util.LinkedList;

// 队列
// 之后要换为增删改查O(1)的队列

/**
 * 连接两个网络节点的channel
 */
public class Channel {
    /**
     * 管理消息经过的路径
     */
    public String id;

    /**
     * channel连接的发送端
     */
    public String sourcePortId;

    /**
     * channel连接的接收端
     */
    public String destPortId;

    /**
     * 队列长度
     */
    private int queueSize;

    /**
     * 接收队列
     */
    private LinkedList<Flow> recvQueue;

    /**
     * 中转发送队列
     */
    @Getter
    private LinkedList<Flow> keepAndSendQueue;

    public Channel(String id, String sourcePortId, String destPortId) {
        this.id = id;
        this.sourcePortId = sourcePortId;
        this.destPortId = destPortId;
        queueSize = 10;
        recvQueue = new LinkedList<>();
        keepAndSendQueue = new LinkedList<>();
    }

    /**
     * @param message >0时要sleep
     * @param delay
     */
    public synchronized void addAfterDelay(Flow message, long delay) {
        // 如果满了，则一直等待
        while (!Thread.currentThread().isInterrupted() && recvQueue.size() == queueSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                // 线程在wait或sleep期间被中断了
                e.printStackTrace();
                Thread.currentThread().interrupt();//重新设置中断标示
            } finally {
                //线程结束前做一些清理工作
                System.out.println(Thread.currentThread().getName() + "在生产者中" + "被中断了");
            }
        }

        if (delay > 0) {
            message.totalDelay += this.id + ": " + delay + "+";
        }
        // 延迟一段时间再加入队列
        recvQueue.add(message);
        System.out.println("发送消息: " + message.id);

        // 呼唤消费者，可以消费
        notify();
    }

    /**
     * @param delay >0时要sleep
     */
    public synchronized void handleMessage(long delay) {
//        while (!Thread.currentThread().isInterrupted() && recvQueue.size() == 0) {
//            try {
//                // 阻塞，等待生产者消费put()
//                wait();
//            } catch (InterruptedException e) {
//                // 线程在wait或sleep期间被中断了
//                e.printStackTrace();
//                Thread.currentThread().interrupt();//重新设置中断标示
//                System.out.println(Thread.currentThread().getName() + "在消费者中" + "被中断了");
//            } finally {
//                //线程结束前做一些清理工作
//            }
//        }
        while (recvQueue.size() == 0) {
            try {
                // 阻塞，等待生产者消费put()
                wait();
            } catch (InterruptedException e) {
                // 线程在wait或sleep期间被中断了
                e.printStackTrace();

                //线程结束前做一些清理工作
                System.out.println(Thread.currentThread().getName() + "在消费者中" + "被中断了");
            }
        }

        // 延迟一段时间再取出队列
        Flow flow = recvQueue.removeFirst();
        if (delay > 0) {
            flow.totalDelay += this.id + ": " + delay + "+";
            flow.totalDelay += this.id + ": " + (System.currentTimeMillis() - flow.generateTime) + "+";
        }
        // 不是最终接收端，则发送给另一个channel
        if (!flow.dest.equals(this.id)) {
            keepAndSendQueue.add(flow);
        }
        System.out.println(flow.id + ": totalDelay=" + flow.totalDelay);

        // 通知生产者继续干活
        notify();
    }
}