package com.NPU.线程八大基础.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 构造函数中新建线程
 */
public class MultiThreadsError6 {
    private Map<String, String> states;

    public MultiThreadsError6() {
        // 构造函数中不能新开线程
        // 解决方案：工厂模式修复
        new Thread(() -> {
            states = new HashMap<>();
            states.put("1", "周一");
            states.put("2", "周二");
            states.put("3", "周三");
            states.put("4", "周四");
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError6 multiThreadError3 = new MultiThreadsError6();
        Map<String, String> states = multiThreadError3.getStates();
        Thread.sleep(2000);
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
