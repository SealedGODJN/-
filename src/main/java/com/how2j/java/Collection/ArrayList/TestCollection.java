package com.how2j.java.Collection.ArrayList;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 数组和容器ArrayList的比较
     */
    public static void test1() {

        //数组的局限性
        Hero heros[] = new Hero[10];
        //声明长度是10的数组
        //不用的数组就浪费了
        //超过10的个数，又放不下
        heros[0] = new Hero("盖伦");
        //放不下要报错
//        heros[20] = new Hero("提莫");

        //容器类ArrayList，用于存放对象
        ArrayList heros1 = new ArrayList();
        heros1.add(new Hero("盖伦"));
        System.out.println(heros1.size());

        // 容器的容量"capacity"会随着对象的增加，自动增长
        // 只需要不断往容器里增加英雄即可，不用担心会出现数组的边界问题。
        heros1.add(new Hero("提莫"));
        System.out.println(heros1.size());
    }

    /**
     * toArray方法
     */
    public static void test2() {

        ArrayList heros2 = new ArrayList();

        // 初始化5个对象
        for (int i = 0; i < 5; i++) {
            heros2.add(new Hero("hero " + i));
        }
        Hero specialHero = new Hero("special hero");
        heros2.add(specialHero);
        System.out.println(heros2);
        Hero hs[] = (Hero[]) heros2.toArray(new Hero[]{});
//        Hero hs[] = (Hero[]) heros2.toArray();
// [Ljava.lang.Object; cannot be cast to [Lcom.how2j.java.Collection.ArrayList.Hero;
        System.out.println("数组:" + hs);
    }

    /**
     * addAll方法
     * 把另外一个容器的对象加入到本容器中
     */
    public static void test3() {

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

    public static void test4() {
        //ArrayList实现了接口List

        //常见的写法会把引用声明为接口List类型
        //注意：是java.util.List,而不是java.awt.List
        //接口引用指向子类对象（多态）

        List heros = new ArrayList();
        heros.add( new Hero("盖伦"));
        System.out.println(heros.size());
    }

    public static void test5() {
        //对于不使用泛型的容器，可以往里面放英雄，也可以往里面放物品
        List heros = new ArrayList();

        heros.add(new Hero("盖伦"));

        //本来用于存放英雄的容器，现在也可以存放物品了
        heros.add(new Item("冰杖"));

        //对象转型会出现问题
        Hero h1=  (Hero) heros.get(0);
        //尤其是在容器里放的对象太多的时候，就记不清楚哪个位置放的是哪种类型的对象了
        Hero h2=  (Hero) heros.get(1);

        //引入泛型Generic
        //声明容器的时候，就指定了这种容器，只能放Hero，放其他的就会出错
        List<Hero> genericheros = new ArrayList<Hero>();
        genericheros.add(new Hero("盖伦"));
        //如果不是Hero类型，根本就放不进去
//        genericheros.add(new Item("冰杖"));

        //除此之外，还能存放Hero的子类
        genericheros.add(new APHero());

        //并且在取出数据的时候，不需要再进行转型了，因为里面肯定是放的Hero或者其子类
        Hero h = genericheros.get(0);
    }

    public static void test6() {
//        为了不使编译器出现警告，需要前后都使用泛型，像这样：
        List<Hero> genericheros = new ArrayList<Hero>();
//        不过JDK7提供了一个可以略微减少代码量的泛型简写方式
        List<Hero> genericheros2 = new ArrayList<>();
//        后面的泛型可以用<>来代替，聊胜于无吧
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
