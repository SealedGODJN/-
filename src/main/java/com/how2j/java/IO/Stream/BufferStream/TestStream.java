package com.how2j.java.IO.Stream.BufferStream;

import java.io.*;

/**
 * 2021-10-15
 * 就好比吃饭，不用缓存就是每吃一口都到锅里去铲。用缓存就是先把饭盛到碗里，碗里的吃完了，再到锅里去铲
 */
public class TestStream {
    public static void test1() {
        // 准备文件lol.txt其中的内容是
//         garen kill teemo
//         teemo revive after 1 minutes
//         teemo try to garen, but killed again
        File f = new File("d:/lol.txt");
        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
        )
        {
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        // 向文件lol2.txt中写入三行语句
        File f = new File("d:/lol2.txt");

        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println("garen kill teemo");
            pw.flush();
            pw.println("teemo revive after 1 minutes");
            pw.flush();
            pw.println("teemo try to garen, but killed again");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设计一个方法，用于移除Java文件中的注释
     * 比如，移出以//开头的注释行
     */
    public static void train1() {
        File f1 = new File("d:/lol.txt");
        File f2 = new File("d:/lol2.txt");
        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (
                FileReader fr = new FileReader(f1);
                BufferedReader br = new BufferedReader(fr);

                // 创建文件字符流
                FileWriter fw = new FileWriter(f2);
                // 缓存流必须建立在一个存在的流的基础上
                PrintWriter pw = new PrintWriter(fw);
        )
        {
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line)
                    break;

                if (line.contains("//")) {
//                    int start = line.indexOf("//"); // 去掉注释
//                    String temp = line.substring(0, start); // 把start之前的东西放进去(tab)
//
//                    start += 2; // "//"占两个
//
//                    line = line.substring(start);
//
//                    line = temp + line;

                } else {
                    System.out.println(line);
                    pw.println(line);
                    pw.flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 别人的方法
     * @param javaFile
     */
    public static void removeComments(File javaFile) {

        StringBuilder sb = new StringBuilder();
        try (
            FileReader reader = new FileReader(javaFile);
            BufferedReader br = new BufferedReader(reader);
        ) {

            while (true) {
                String line = br.readLine();
                if (null == line)
                    break;
                if (!line.trim().startsWith("//"))
                    sb.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
            FileWriter writer = new FileWriter(javaFile);
            BufferedWriter bw = new BufferedWriter(writer)
        )
        {
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public static void main(String[] args) {
//        test1();
//        test2();
//        train1();
        File file = new File("d:/lol.txt");
        removeComments(file);
    }
}
