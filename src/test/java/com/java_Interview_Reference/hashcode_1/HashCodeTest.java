package com.java_Interview_Reference.hashcode_1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HashCodeTest {
    private Set<String> words;

    @Before
    public void before() {
        "abc".hashCode();
        // 读取文件
        words = FileUtil.readWordList("D:\\IDEA_PROGECT\\helloworld\\java面经-小傅哥\\103976个英语单词库.txt");
    }

    @Test
    public void test_collisionRate() {
        List<RateInfo> rateInfoList = HashCode1.collisionRateList(words, 2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199);
        for (RateInfo rate : rateInfoList) {
            System.out.println(String.format("乘数 = %4d, 最小Hash = %11d, 最大Hash = %10d, 碰撞数量 =%6d, 碰撞概率 = %.4f%%",
                    rate.getMultiplier(), rate.getMinHash(), rate.getMaxHash(), rate.getCollisionCount(), rate.getCollisionRate() * 100));
        }
    }

    @Test
    public void test_hashArea() {
        System.out.println(HashCode1.hashArea(words, 41).values());
        System.out.println(HashCode1.hashArea(words, 7).values());
        System.out.println(HashCode1.hashArea(words, 31).values());
        System.out.println(HashCode1.hashArea(words, 39).values());
        System.out.println(HashCode1.hashArea(words, 199).values());
    }
}
