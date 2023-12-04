package com.NPU.上海出差.模拟事件仿真.队列;

import java.util.HashMap;
import java.util.Map;

public class Port implements Runnable {

    /**
     * 独一无二的id
     */
    public String id;

    /**
     * 端口通过line连接到另一个端口
     */
    private Map<String, Channel> lines;

    /**
     * 最终发送端
     * 只能发送消息
     */
    private boolean isOnlySend;

    /**
     * 生产消息的频率
     */
    private long refreshPeriod;

    public Port(String id, boolean isOnlySend, Map<String, Channel> channels, long refreshPeriod) {
        this.id = id;
        this.isOnlySend = isOnlySend;
        this.lines = channels;
        this.refreshPeriod = refreshPeriod;
    }

    public void generateFlow() throws InterruptedException {
        int i = 0;
        while (i < 10) {
            Flow f1 = new Flow();
            f1.id = "flow" + i;
            i++;
            f1.generateTime = System.currentTimeMillis();
            f1.totalDelay = "";
            f1.source = "A653send";
            f1.dest = "A653recv";
            f1.nextChannelId.add("channel1");
            f1.nextChannelId.add("channel2");

            sendByChannelId(f1);
        }
        System.out.println();
    }

    /**
     * 根据flow的nextChannelId，决定把flow发送到哪一条channel
     *
     * @param f1
     */
    private void sendByChannelId(Flow f1) {
        for (String id :
                lines.keySet()) {
            // 保证索引在数组安全范围内
            if (f1.currentIndex < f1.nextChannelId.size() && id.equals(f1.nextChannelId.get(f1.currentIndex))) {
                f1.currentIndex++;
                lines.get(id).addAfterDelay(f1, refreshPeriod);
            }
        }
    }

    public void recvFlow() throws InterruptedException {
        while (true) {
            // 如果消息到达终点了，则不继续处理
            for (String id :
                    lines.keySet()) {
                Channel channel = lines.get(id);
                // 分情况
                channel.handleMessage(refreshPeriod);
                if (channel.getKeepAndSendQueue().size() != 0) {
                    // 之后改为多线程的发送
                    for (Flow f :
                            channel.getKeepAndSendQueue()) {
                        sendByChannelId(f);
                    }
                    // 第二种实现：每次只发一个
                }
            }
        }
    }

    @Override
    public void run() {
        if (isOnlySend) {
            try {
                System.out.println(Thread.currentThread().getName() + "==开始生产消息==");
                generateFlow();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "生产消息" + "被中断了");
                throw new RuntimeException(e);
            }
        } else {
            try {
                System.out.println(Thread.currentThread().getName() + "==开始接收消息==");
                recvFlow();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "接收消息" + "被中断了");
                throw new RuntimeException(e);
            }
        }
    }
}
