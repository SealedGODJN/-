package com.interview.alibaba;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*4);

        // Q: 初始化之后，这三个值分别是多少呢？
        System.out.println("position: " + byteBuffer.position()); // 0
        System.out.println("capacity: " + byteBuffer.capacity()); // 1024*4
        System.out.println("limit: " + byteBuffer.limit()); // 1024*4

        //向ByteBuffer写入数据
        byteBuffer.put("hello, 喜欢天文的pony站长~".getBytes(StandardCharsets.UTF_8));

//        // Q: 向ByteBuffer中写入数据之后，哪些值会发生变化呢？
//        System.out.println(StringUtils.repeat("=", 10) + "写入数据之后" + StringUtils.repeat("=", 10));
//        System.out.println("position: " + byteBuffer.position());
//        System.out.println("capacity: " + byteBuffer.capacity());
//        System.out.println("limit: " + byteBuffer.limit());
    }
}
