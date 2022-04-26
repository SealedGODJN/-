package com.NPU.面向对象设计.chapter6;

import com.NPU.面向对象设计.chapter4.Point2D;

public class Pair<A, B> {
    private A element1;
    private B element2;

    public Pair(A element1, B element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public A getElement1() {
        return element1;
    }

    public B getElement2() {
        return element2;
    }

    public static void main(String[] args) {
        Pair<String, String> pairOne =
                new Pair<String, String>("1", "2");
        System.out.println(pairOne.getElement1() + " "
                + pairOne.getElement2());
        Pair<Point2D, Point2D> pairTwo =
                new Pair<Point2D, Point2D>(new Point2D(100, 200), new Point2D(300, 400));
        System.out.println(pairTwo.getElement1() + " "
                + pairTwo.getElement2());
    }
}