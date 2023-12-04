package com.NPU.上海出差.模拟事件仿真.队列;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static Map<String, Flow> allFlow = new HashMap<>();

    public static Map<String, Port> allPort = new HashMap<>();

    /**
     * 管理所有的channel
     */
    public static Map<String, Channel> allChannel = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // 初始t0
        long t0 = System.currentTimeMillis();

        Channel channel = new Channel("channel1", "A653send", "A653keep");

        Channel channel1 = new Channel("channel2", "A653keep", "A653recv");

        Map<String, Channel> c1 = new HashMap<>();
        c1.put(channel.id, channel);

        Map<String, Channel> c2 = new HashMap<>();
        c2.put(channel.id, channel);
        c2.put(channel1.id, channel1);

        Map<String, Channel> c3 = new HashMap<>();
        c3.put(channel1.id, channel1);

        Port a653send = new Port("A653send", true, c1, 200);
        Port a653keep = new Port("A653keep", false, c2, 50);
        Port a653recv = new Port("A653recv", false, c3, 50);

        allPort.put(a653send.id, a653send);
        allPort.put(a653keep.id, a653keep);
        allPort.put(a653recv.id, a653recv);

        ExecutorService e = Executors.newFixedThreadPool(10);
        for (String id : allPort.keySet()) {
            System.out.println("添加端口: " + id);
            e.execute(allPort.get(id));
        }

//        Thread.sleep(10000);
        // 被中断的任务列表
//        List<Runnable> runnableList = e.shutdownNow();
    }
}