package com.NPU.面向对象设计.chapter6;

import com.NPU.面向对象设计.chapter3.Product;
import com.NPU.面向对象设计.chapter4.Point2D;

public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.toString());
    }

    public static <T> T aMethod(T anObject) {
        return anObject;
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("Hello");
        gm.f(new Product("coffee", 27, 34));
        gm.f(new Point2D(23, 45));

        String greeting = "Hi";
        String reply = aMethod(greeting);
        System.out.println(reply);
    }
}
