package com.how2j.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.how2j.bean.Hero;

public class TestLambda {
    public static void main(String[] args) {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 10; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("筛选出 hp>100 && damange<50的英雄");


//        test1(heros);
//        test2(heros);
//        filter2(heros, h -> h.hp > 100 && h.damage < 50); // 如何变成lambda表达式？

        test3(heros);

    }

    public static void test3(List<Hero> heros) {
        // 匿名类的正常写法
        HeroChecker c1 = new HeroChecker() {
            @Override
            public boolean test(Hero h) {
                return (h.hp > 100 && h.damage < 50);
            }
        };
        // 把new HeroChcekcer，方法名，方法返回类型信息去掉
        // 只保留方法参数和方法体
        // 参数和方法体之间加上符号 ->
        HeroChecker c2 = (Hero h) -> {
            return h.hp > 100 && h.damage < 50;
        };

        // 把return和{}去掉
        HeroChecker c3 = (Hero h) -> h.hp > 100 && h.damage < 50;

        // 把 参数类型和圆括号去掉
        HeroChecker c4 = h -> h.hp > 100 && h.damage < 50;

        // 把c4作为参数传递进去
        filter2(heros, c4);

        // 直接把表达式传递进去
        filter2(heros, h -> h.hp > 100 && h.damage < 50);
    }

    public static void test2(List<Hero> heros) {
        HeroChecker heroChecker = new HeroChecker() {
            @Override
            public boolean test(Hero hero) {
                return hero.hp > 100 && hero.damage < 50;
            }
        };
        filter2(heros, heroChecker);
    }

    private static void filter2(List<Hero> heroes, HeroChecker heroChecker) {
        for (Hero hero : heroes) {
            if(heroChecker.test(hero)) {
                System.out.println(hero);
            }
        }
    }

    public static void test1(List<Hero> heros) {
        filter(heros);
    }

    private static void filter(List<Hero> heros) {
        for (Hero hero : heros) {
            if(hero.hp>100 && hero.damage<50)
                System.out.print(hero);
        }
    }

}