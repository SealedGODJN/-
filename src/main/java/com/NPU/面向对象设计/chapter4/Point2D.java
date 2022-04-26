package com.NPU.面向对象设计.chapter4;

public class Point2D {

    private float x;
    private float y;

    public Point2D(float initialX, float initialY) {

        x = initialX;
        y = initialY;
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    public static void main(String[] args) {

        Point2D pointOne = new Point2D(100, 200);
        System.out.println(pointOne.toString());
        System.out.println(pointOne);
    }
}
