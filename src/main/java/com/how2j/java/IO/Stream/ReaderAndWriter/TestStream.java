package com.how2j.java.IO.Stream.ReaderAndWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestStream {

    public static void test1() {
        // 准备文件lol.txt其中的内容是AB
        File f = new File("d:/lol.txt");
        // 创建基于文件的Reader
        try (FileReader fr = new FileReader(f)) {
            // 创建字符数组，其长度就是文件的长度
            char[] all = new char[(int) f.length()];
            // 以字符流的形式读取文件所有内容
            fr.read(all);
            for (char b : all) {
                // 打印出来是A B
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        // 准备文件lol2.txt
        File f = new File("d:/lol2.txt");
        // 创建基于文件的Writer
        try (FileWriter fr = new FileWriter(f)) {
            // 以字符流的形式把数据写入到文件中
            String data="abcdefg1234567890";
            char[] cs = data.toCharArray();
            fr.write(cs);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void encodeFile(File encodingFile, File encodedFile) throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(encodingFile);
            fw = new FileWriter(encodedFile);


            // 创建字符数组，其长度就是文件的长度
            char[] all = new char[(int) encodingFile.length()];
            // 以字符流的形式读取文件所有内容
            fr.read(all);
            for (char b : all) {
                // 打印出来是A B
                System.out.println(b);

                if (b>='0' && b<='9') {
                    if (b != '9') {
                        b++;
                    } else {
                        b = '0';
                    }
                }
                else if (b >='a' && b <= 'z') {
                    if (b != 'z') {
                        b++;
                    } else {
                        b = 'a';
                    }
                } else if (b >='A' && b <= 'Z') {
                    if (b != 'Z') {
                        b++;
                    } else {
                        b = 'A';
                    }
                }
                fw.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fr != null;
            fr.close();
            assert fw != null;
            fw.close();
        }
    }

    public static void decodeFile(File decodingFile, File decodedFile) throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(decodingFile);
            fw = new FileWriter(decodedFile);


            // 创建字符数组，其长度就是文件的长度
            char[] all = new char[(int) decodingFile.length()];
            // 以字符流的形式读取文件所有内容
            fr.read(all);
            for (char b : all) {
                // 打印出来是A B
                System.out.println(b);

                if (b>='0' && b<='9') {
                    if (b != '0') {
                        b--;
                    } else {
                        b = '9';
                    }
                }
                else if (b >='a' && b <= 'z') {
                    if (b != 'a') {
                        b--;
                    } else {
                        b = 'z';
                    }
                } else if (b >='A' && b <= 'Z') {
                    if (b != 'A') {
                        b--;
                    } else {
                        b = 'Z';
                    }
                }
                fw.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fr != null;
            fr.close();
            assert fw != null;
            fw.close();
        }
    }

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
        File file1 = new File("d:/lol.txt");
        File file2 = new File("d:/lol2.txt");
//        encodeFile(file1, file2);
        decodeFile(file1, file2);
    }
}