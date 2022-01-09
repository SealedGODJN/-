package com.java_Interview_Reference.hashcode_2;

import com.alibaba.excel.EasyExcel;
import com.java_Interview_Reference.hashcode_1.FileUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDisturb {
    private Set<String> words;

    @Before
    public void before() {
        "abc".hashCode();
        // 读取文件
        words = FileUtil.readWordList("D:\\IDEA_PROGECT\\helloworld\\java面经-小傅哥\\103976个英语单词库.txt");
    }

    // 10万单词已经初始化到words中
    @Test
    public void test_disturb() {
        Map<Integer, Integer> map = new HashMap<>(16);
        for(String word : words) {
            // 使用扰动函数
            int idx = Disturb.disturbHashIdx(word, 128); // size 应该小于原数组长度
//            int idx = Disturb.disturbHashIdx(word, words.size());
            // 不适用扰动函数
//            int idx = Disturb.hashIdx(word, 128);
//            int idx = Disturb.hashIdx(word, words.size());
            if (map.containsKey(idx)) {
                Integer integer = map.get(idx);
                map.put(idx, integer+1); // 记录每个key对应的string,观察扰动函数的效果
            } else {
                map.put(idx, 1);
            }
        }
//        System.out.println(map.size());
//        System.out.println(map.values());
        map.forEach((key, value) -> {
            System.out.print(value + " ");
        });
        System.out.println();

        /**
         * 写excel
         */
//        String fileName = "D:\\IDEA_PROGECT\\helloworld\\java面经-小傅哥\\" + "disturb.xlsx";
//        EasyExcel.write(fileName).sheet("sheet1").doWrite(Arrays.asList(map.values().toArray()));
//        EasyExcel.write(fileName).sheet("sheet1").doWrite(Arrays.asList(words.toArray()));

        /**
         * 计算方差
         */
        // 求和
        AtomicInteger sum = new AtomicInteger();
        map.forEach((key, value) -> {
            sum.addAndGet(value);
        });
        // 平均值
        int avg = Integer.parseInt(sum.toString());
        avg = avg / map.size();
        // 计算xi-x
        List<Integer> fangcha = new ArrayList<>();
        for(Integer value : map.values()) {
            fangcha.add(value - avg);
            System.out.print(value-avg + " ");
        }
        int fangCha = 0;
        for (Integer i : fangcha) {
            fangCha += i * i;
        }
        fangCha /= map.size();
        System.out.println();
        System.out.println(fangCha);
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

//    public HashMap(int initialCapacity, float loadFactor) {
//    ...
//        this.loadFactor = loadFactor;

    /**
     * 阀值threshold，通过方法tableSizeFor进行计算，是根据初始化来计算的。
     *     这个方法也就是要寻找比初始值大的，最小的那个2进制数值。比如传了17，我应该找到的是32。
     */
//        this.threshold = tableSizeFor(initialCapacity);
//    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

// MAXIMUM_CAPACITY = 1 << 30，这个是临界范围，也就是最大的Map集合。
// 乍一看可能有点晕😵怎么都在向右移位1、2、4、8、16，
// 这主要是为了把二进制的各个位置都填上1，当二进制的各个位置都是1以后，就是一个标准的2的倍数减1了，最后把结果加1再返回即可。

    @Test
    public void test_hashMap() {
        List<String> list = new ArrayList<>();
        list.add("jlkk");
        list.add("lopi");
        list.add("jmdw");
        list.add("e4we");
        list.add("io98");
        list.add("nmhg");
        list.add("vfg6");
        list.add("gfrt");
        list.add("alpo");
        list.add("vfbh");
        list.add("bnhj");
        list.add("zuio");
        list.add("iu8e");
        list.add("yhjk");
        list.add("plop");
        list.add("dd0p");
        for (String key : list) {
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);
            System.out.println("字符串：" + key + " \tIdx(16)【扩容之前】：" + ((16 - 1) & hash) + " \tBit值：" + Integer.toBinaryString(hash) + " - " + Integer.toBinaryString(hash & 16) + " \t\tIdx(32)【扩容之后】：" + ((32 - 1) & hash));
            System.out.println(Integer.toBinaryString(key.hashCode()) +" "+ Integer.toBinaryString(hash) + " " + Integer.toBinaryString((32 - 1) & hash));
        }
        // 原哈希值与扩容新增出来的长度16，进行&运算，如果值等于0，则下标位置不变。
        // 如果不为0，那么新的位置则是原来位置上加16。｛这个地方需要好好理解下，并看实验数据｝
    }

}
