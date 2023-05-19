package com.NPU.线程八大基础.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 发布（逸出）
 */
public class MultiThreadError3 {
    private Map<String, String> states;

    public MultiThreadError3() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }

    /**
     * 返回一个副本
     *
     * @return A copy of states
     */
    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadError3 multiThreadsError3 = new MultiThreadError3();
        Map<String, String> states = multiThreadsError3.getStates();
//        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));
        System.out.println(multiThreadsError3.getStatesImproved().get("1"));
        multiThreadsError3.getStatesImproved().remove("1");
        System.out.println(multiThreadsError3.getStatesImproved().get("1"));
    }
}
