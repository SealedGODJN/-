package com.java.review.Collection.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<Integer> test1 = new ArrayList<>();
        List<Integer> test2 = new LinkedList<>();

        for (int i=0; i < 10; i++) {
            test1.add(i);
            test2.add(i);
        }

        for(int i=0; i < 10; i++) {
            System.out.println("test1\t"+test1.get(i));
            System.out.println("test2\t"+test2.get(i));
        }
    }
}
