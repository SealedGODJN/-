package com.java.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelDemo {
    public static void main(String args[]) throws IOException {
        String relativelyPath = System.getProperty("user.dir");
        FileInputStream input = new FileInputStream(relativelyPath + "/testin.txt");
        ReadableByteChannel source = input.getChannel();
        FileOutputStream output = new FileOutputStream(relativelyPath + "/testout.txt");
        WritableByteChannel destination = output.getChannel();
        copyData(source, destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (src.read(buffer) != -1) { // 读模式，从通道上读取数据，写入缓冲区
            // The buffer is used to drained
            buffer.flip(); // 将缓存字节数组的指针设置为数组的开始序列即数组下标0。将读模式切换为写模式
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer); // 从缓冲区中读取数据，写入通道
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
            //在逻辑上清空ByteBuffer里的数据，实际上不清空数据
        }
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_nio/java-nio-channels.html#article-start

