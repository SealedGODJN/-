package com.NPU.面向对象设计.chapter3.apache.common.collections;

import org.apache.commons.collections4.map.AbstractReferenceMap;
import org.apache.commons.collections4.map.ReferenceMap;

public class TestRerfeneceMap {
    public static void main(String[] args) {
        //当前JVM占用的内存总数(M)
        double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);

        //JVM最大可用内存总数(M)
        double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);

        //JVM空闲内存(M)
        double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);

        //可用内存内存(M)
        double mayuse = (max - total + free);

        //已经使用内存(M)
        double used = (total - free);

        // key value全部使用软引用，在JVM内存不足的情况下GC会回收软引用的键值对
        ReferenceMap<String, String> mapSOFT = new ReferenceMap<>(AbstractReferenceMap.ReferenceStrength.SOFT, AbstractReferenceMap.ReferenceStrength.SOFT);

        for (int i = 0; i < 100000000; i++) {
            mapSOFT.put(String.valueOf(i), String.valueOf(i));
//            System.out.println("JVM当前空闲内存："+free);
        }
        System.out.println("JVM当前空闲内存：" + free);
        System.out.println("JVM当前占用内存：" + total);

        // key value全部使用强引用，在JVM内存不足的情况下GC不会回收强引用的键值对
        ReferenceMap<String, String> mapHARD = new ReferenceMap<>(AbstractReferenceMap.ReferenceStrength.HARD, AbstractReferenceMap.ReferenceStrength.HARD);

        for (int i = 0; i < 100000000; i++) {
            mapHARD.put(String.valueOf(i), String.valueOf(i));

        }
        System.out.println("JVM当前空闲内存：" + free);
        System.out.println("JVM当前占用内存：" + total);
    }
}
