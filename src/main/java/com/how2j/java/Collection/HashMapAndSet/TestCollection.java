package com.how2j.java.Collection.HashMapAndSet;

import java.util.*;

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

    public static void test3() {
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

    public static void train1() {
        ArrayList<Hero> threethrousandthrousand = new ArrayList<>();
        HashMap<Hero, String> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 3000000; i++) {
            Hero temp = new Hero();
            String code = String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10));
            temp.name = "hero-" + code;
            // 生成一个随机的 int 值，该值介于 [0,n)，包含 0 而不包含 n。如果想生成
            //  指定区间的 int 值，也需要进行一定的数学变换
            threethrousandthrousand.add(temp);
            map.put(temp, temp.name);
        }

        long start = System.currentTimeMillis();
        for (Hero hero : threethrousandthrousand) {
            if(hero.name.equals("hero-5555")) {
                System.out.println(hero);
            }
        }
        long end = System.currentTimeMillis();

        long first = end - start;

        start = System.currentTimeMillis();
        for (Hero hero : map.keySet()){
            if(hero.name.equals("hero-5555")) {
                System.out.println(hero);
            }
        }
        end = System.currentTimeMillis();


        System.out.println(first);
        System.out.println(end - start);
    }

    public static void train1_1() {
        List<Hero> hs =new ArrayList<>();
        System.out.println("初始化开始");
        for (int i = 0; i < 3000000; i++) {
            Hero h = new Hero(   "hero-" + random());
            hs.add(h);
        }
        //名字作为key
        //名字相同的hero，放在一个List中，作为value
        HashMap<String,List<Hero>> heroMap =new HashMap();
        for (Hero h : hs) {
            List<Hero> list = heroMap.computeIfAbsent(h.name, k -> new ArrayList<>());
            list.add(h);
        }

        System.out.println("初始化结束");
        System.out.println("开始查找");
        findByIteration(hs);
        findByMap(heroMap);
    }

    private static List<Hero> findByMap(HashMap<String,List<Hero>> m) {
        long start =System.currentTimeMillis();
        List <Hero>result= m.get("hero-5555");
        long end =System.currentTimeMillis();
        System.out.printf("通过map查找，一共找到%d个英雄，耗时%d 毫秒%n",result.size(),end-start);
        return result;
    }
    private static List<Hero> findByIteration (List<Hero> hs) {
        long start =System.currentTimeMillis();
        List<Hero> result =new ArrayList<>();
        for (Hero h : hs) {
            if(h.name.equals("hero-5555")){
                result.add(h);
            }
        }
        long end =System.currentTimeMillis();
        System.out.printf("通过for查找，一共找到%d个英雄，耗时%d 毫秒%n", result.size(),end-start);
        return result;
    }
    public static int random(){
        return ((int)(Math.random()*9000)+1000);
    }

    public static void test4() {
        HashSet<String> names = new HashSet<String>();

        names.add("gareen");

        System.out.println(names);

        //第二次插入同样的数据，是插不进去的，容器中只会保留一个
        names.add("gareen");
        System.out.println(names);
    }

    public static void test5() {
        HashSet<Integer> numbers = new HashSet<Integer>();

        numbers.add(9);
        numbers.add(5);
        numbers.add(1);

        // Set中的元素排列，不是按照插入顺序
        System.out.println(numbers);

    }

    public static void test6() {
        HashSet<Integer> numbers = new HashSet<Integer>();

        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }

        //Set不提供get方法来获取指定位置的元素
        //numbers.get(0)

        //遍历Set可以采用迭代器iterator
        for (Iterator<Integer> iterator = numbers.iterator(); iterator.hasNext();) {
            Integer i = (Integer) iterator.next();
            System.out.println(i);
        }

        //或者采用增强型for循环
        for (Integer i : numbers) {
            System.out.println(i);
        }
    }

    public static void train2() {
        String[] array = new String[100];
        for (int i = 0; i < array.length; i++) {
//            array[i] = getRandomString(2);
            array[i] = randomString(2);
        }
        HashSet<String> isSame = new HashSet<>();
        for (String s : array) {
            if (isSame.contains(s)) {
                System.out.println(s);
            } else isSame.add(s);
        }
    }

    //生成指定length的随机字符串（A-Z，a-z，0-9）
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    private static String randomString(int length) {
        String pool = "";
        for (short i = '0'; i <= '9'; i++) {
            pool += (char) i;
        }
        for (short i = 'a'; i <= 'z'; i++) {
            pool += (char) i;
        }
        for (short i = 'A'; i <= 'Z'; i++) {
            pool += (char) i;
        }
        char cs[] = new char[length];
        for (int i = 0; i < cs.length; i++) {
            int index = (int) (Math.random() * pool.length());
            cs[i] = pool.charAt(index);
        }
        String result = new String(cs);
        return result;
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        train1();
//        train1_1();
//        test4();
//        test5();
//        test6();
        train2();
    }

}
