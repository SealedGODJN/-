package com.how2j.java.IO.Stream;

import com.leetcode.Test;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestStream {

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.version").contains("windows");
    }

    public static void test1() {
        FileInputStream fis = null;
        try{
            File f;
            if(isWindows()) {
                f = new File("d:/lol.txt");
            } else f = new File("/Users/hjn/Desktop/IDEA_PROJECT/LoLFolder/lol.txt");

            if(!f.exists()) f.createNewFile();

            fis = new FileInputStream(f);
            // 通过这个输入流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try{
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void train1() {
        FileOutputStream fileOutputStream = null;
        try{
            File f;
            if(isWindows()) {
                f = new File("d:/lol.txt");
            } else f = new File("/Users/hjn/Desktop/IDEA_PROJECT/LoLFolder/lol.txt");

            if(!f.exists()) f.createNewFile();

            fileOutputStream = new FileOutputStream(f);
            // 通过这个输入流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try{
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void test2() {
        try {
            //准备文件lol.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File("d:/lol.txt");
            //创建基于文件的输入流
            FileInputStream fis =new FileInputStream(f);
            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[(int) f.length()];
            //以字节流的形式读取文件所有内容
            fis.read(all);
            for (byte b : all) {
                //打印出来是65 66
                System.out.print((char)b);
            }

            //每次使用完流，都应该进行关闭
            fis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void test3() {
        try {
            // 准备文件lol2.txt其中的内容是空的
            File f = new File("d:/lol2.txt");
            // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
            byte data[] = { 88, 89 };

            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        test1();
//        train1();
//        test2();
        test3();
    }
}
