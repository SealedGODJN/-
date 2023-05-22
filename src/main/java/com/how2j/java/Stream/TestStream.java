package com.how2j.java.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        //直接通过集合的stream接口创建
        List<String> roles = Arrays.asList("鲁班七号", "伽罗", "云中君");
        Stream<String> stream = roles.stream();
        //Arrays.stream() 创建流
        String[] roles2 = {"盖伦", "卡特", "剑魔"};
        Stream<String> stream2 = Arrays.stream(roles2);
        Stream<String> stream3 = Stream.of("混沌", "枪手", "超英");

        System.out.println(stream);
        System.out.println(stream2);
        System.out.println(stream3);
    }
}
