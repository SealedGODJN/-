package com.how2j.java.Collection.HashMapAndSet;

import java.util.HashMap;

public class TestCollection {
    public static void test1() {
        HashMap<String,String> dictionary = new HashMap<>();
        dictionary.put("adc", "物理英雄");
        dictionary.put("apc", "魔法英雄");
        dictionary.put("t", "坦克");

        System.out.println(dictionary.get("t"));
    }

    public static void test2() {
        HashMap<String,Hero> heroMap = new HashMap<String,Hero>();

        heroMap.put("gareen", new Hero("gareen1"));
        System.out.println(heroMap);

        //key为gareen已经有value了，再以gareen作为key放入数据，会导致原英雄，被覆盖
        //不会增加新的元素到Map中
        heroMap.put("gareen", new Hero("gareen2"));
        System.out.println(heroMap);

        //清空map
        heroMap.clear();
        Hero gareen = new Hero("gareen");

        //同一个对象可以作为值插入到map中，只要对应的key不一样
        heroMap.put("hero1", gareen);
        heroMap.put("hero2", gareen);

        System.out.println(heroMap);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        String test = "my name is user";
        HashMap<String, String > test1 = new HashMap<>();
        int hashCode = test.hashCode(); // -2068959648 1000 0100 1010 1110 0010 1110 0110 0000
        test1.put(test, test); // hash = -2068959648
        System.out.println(hashCode); // -2068959648
        System.out.println(hashCode >>> 16); // 33966 0000 0000 0000 0000 1000 0100 1010 1110
        System.out.println(hashCode ^ (hashCode >>> 16)); // -2068927794 1000 0100 1010 1110 1010 1010 1100 1110
        System.out.println(hashCode ^ 33966); // -2068927794
        System.out.println(-2068959648 ^ 33966); // -2068927794
    }

}
