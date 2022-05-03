package com.java.Generic.improve;

public class Test {
    public static void main(String[] args) {
        Integer i1 = 1;
        Integer i2 = 1;
        List<Integer> test = new Cons<>(i1, null);
        List<Integer> test2 = new Cons<>(i2, test);

        AddVisitor add = new AddVisitor();
        System.out.println(add._case((Cons) test2));

    }
}
