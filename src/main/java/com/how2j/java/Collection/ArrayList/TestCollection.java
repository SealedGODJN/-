package com.how2j.java.Collection.ArrayList;

import java.util.ArrayList;

public class TestCollection {
    public static void isExistByName(ArrayList<Hero> arrayList) {
        for (Hero i :
                arrayList) {
            if (i.name.equals("hero 1")) {
                System.out.println("找到了name为hero 1的对象");
                return;
            }
        }
    }

    public static void main(String[] args) {

        /**
         * 数组和容器ArrayList的比较
         */
//        //数组的局限性
//        Hero heros[] = new Hero[10];
//        //声明长度是10的数组
//        //不用的数组就浪费了
//        //超过10的个数，又放不下
//        heros[0] = new Hero("盖伦");
//        //放不下要报错
//        heros[20] = new Hero("提莫");
//
//        //容器类ArrayList，用于存放对象
//        ArrayList heros1 = new ArrayList();
//        heros1.add(new Hero("盖伦"));
//        System.out.println(heros1.size());
//
//        //容器的容量"capacity"会随着对象的增加，自动增长
//        //只需要不断往容器里增加英雄即可，不用担心会出现数组的边界问题。
//        heros1.add(new Hero("提莫"));
//        System.out.println(heros1.size());

        /**
         * toArray方法
         */
//        ArrayList heros2 = new ArrayList();
//
//        // 初始化5个对象
//        for (int i = 0; i < 5; i++) {
//            heros2.add(new Hero("hero " + i));
//        }
//        Hero specialHero = new Hero("special hero");
//        heros2.add(specialHero);
//        System.out.println(heros2);
//        Hero hs[] = (Hero[]) heros2.toArray(new Hero[]{});
//        System.out.println("数组:" + hs);

        /**
         * addAll方法
         * 把另外一个容器的对象加入到本容器中
         */

        ArrayList heros3 = new ArrayList();

        // 初始化5个对象
        for (int i = 0; i < 5; i++) {
            heros3.add(new Hero("hero " + i));
        }

        System.out.println("ArrayList heros:\t" + heros3);

        //把另一个容器里所有的元素，都加入到该容器里来
        ArrayList anotherHeros = new ArrayList();
        anotherHeros.add(new Hero("hero a"));
        anotherHeros.add(new Hero("hero b"));
        anotherHeros.add(new Hero("hero c"));
        System.out.println("anotherHeros heros:\t" + anotherHeros);
        heros3.addAll(anotherHeros);
        System.out.println("把另一个ArrayList的元素都加入到当前ArrayList:");
        System.out.println("ArrayList heros:\t" + heros3);

        TestCollection.isExistByName(heros3);
    }
}
