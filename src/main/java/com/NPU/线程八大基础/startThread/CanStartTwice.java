package com.NPU.线程八大基础.startThread;

public class CanStartTwice {
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        t.start();
    }
}
