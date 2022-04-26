package com.NPU.面向对象设计.chapter6;

import com.NPU.面向对象设计.chapter4.Point2D;

public class Array {
    public static void main(String[] args) {
//        int[] ages = {21, 19, 35, 27, 55};
//        String[] names = {"Bob", "Achebe", null};
        String[] names = new String[]{"Bob", "Achebe", null};
        Point2D[] points = new Point2D[]{new Point2D(1, 1), new Point2D(2, 3)};

        int[] ages = new int[5];
        for (int index = 0; index < ages.length; index++) {
            int x = ages[index];
        }
    }
}
