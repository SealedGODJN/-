package com.NPU.线程八大基础.ThreadSafe;

/**
 * @description 初始化未完毕，就this赋值
 */
public class MultiThreadError4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(100);
        if (point != null) {
            System.out.println(point);
        } else System.out.println("null");
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadError4.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}