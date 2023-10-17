package com.how2j.java.IO.Stream.ReaderAndWriter;

import java.io.*;

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

    public static void test3() throws IOException {
        String fileName =  "D:/lol.txt";
        String content = "EnglishTest_E001_Translate the following text into English._2_Smooth,fluent and without language problems or wrong words_C-E\n" +
                "EnglishTest_E002_Translate the following article content._3_Clear logic and no language problems_E-C";

        OutputStreamWriter fOut = null;
        try {
             fOut= new OutputStreamWriter(new FileOutputStream(fileName));
            char[] chars = new char[content.length()];
            fOut.write(chars);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fOut.close();
        }
    }

    /**
     * UTF-8编码方式(二进制)
     * 0xxxxxxx ASCII码
     * 110xxxxx 10xxxxxx
     * 1110xxxx 10xxxxxx 10xxxxxx
     * 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * <p>
     * 根据上面表格，要解析 UTF-8 编码就很简单了，如果一个字节第一位是 0 ，则这个字节就是一个单独的字符，如果第一位是 1 ，则连续有多少个 1 ，就表示当前字符占用多少个字节
     */
    public static void test4() {
        // 了解二进制
//            String binaryValue = "11100100"; // 二进制值
//            int hexadecimalValue = Integer.parseInt(binaryValue, 2);
//
//            System.out.println("二进制值：" + binaryValue);
//            System.out.println("十六进制值：" + Integer.toHexString(hexadecimalValue));

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:\\test\\Fifo.a");
            // 当写入的整数无法在字符集编码中找到对应的字符时，
            // 字符流读取会将其转换成Unicode Character ‘REPLACEMENT CHARACTER’ (U+FFFD)：65533
            fos.write(7870);
            // java中字符流一次到底读几个字节？由于输入的整数找不到对应的字符，故只读1个字节。
            // 如果输入这3个字节
            // test5中的char[]数组只有一个元素，20320（不强转成long将输出字符“你”）

            // 1110 0100 (代表这个utf-8字符是由3个8bit二进制数构成)
//            fos.write(0xe4);
            // 1011 1100
//            fos.write(0xbd);
            // 1010 0000
//            fos.write(0xa0);

            // java字符流一次只读一个字节
//            fos.write(0xe4bda0);

            // java字符流一次只读一个字节
//            fos.write(0xe4bd);
//            fos.write(0xa0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test5() {
        FileReader fr = null;
        try {
            fr = new FileReader("D:\\test\\Fifo.a");

            char[] data = new char[10];
            int len;
            while ((len = fr.read(data)) != -1) {
                for (int i = 0; i < len; i++) {
//                    System.out.println((long) data[i]);
                    System.out.println(data[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        File file1 = new File("d:/test/lol.txt");
        File file2 = new File("d:/test/lol2.txt");
        encodeFile(file1, file2);
        decodeFile(file1, file2);

//        test4();
//        test5();
    }
}