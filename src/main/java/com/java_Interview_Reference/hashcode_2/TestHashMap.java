package com.java_Interview_Reference.hashcode_2;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>(16, 0.5f);
        System.out.println(hashMap);
    }
}
