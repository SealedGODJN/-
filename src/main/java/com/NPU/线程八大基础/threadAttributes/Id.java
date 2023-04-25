package com.NPU.线程八大基础.threadAttributes;

/**
 * @description ID从1开始，JVM运行起来后，我们创建的ID不是2
 */
public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID" + Thread.currentThread().getId());
        System.out.println("子线程的ID" + thread.getId());
    }
}
