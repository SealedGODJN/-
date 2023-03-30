package com.how2j.java.IO.Stream.ByteStream;

import java.io.*;

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
            e.printStackTrace();
        }
    }

    /**
     * 以字节流的形式向文件写入数据 中的例子，当lol2.txt不存在的时候，是会自动创建lol2.txt文件的。
     * 但是，如果是写入数据到d:/xyz/lol2.txt，而目录xyz又不存在的话，就会抛出异常。
     * 那么怎么自动创建xyz目录？
     * 如果是多层目录 d:/xyz/abc/def/lol2.txt 呢？
     */
    public static void train2() {
        String path = "d:/xyz/abc/def/lol2.txt";
        String[] directories = path.split("/");
        StringBuilder filePath = new StringBuilder(directories[0]);
        int end = directories.length-1; // 最后一个子文件夹
        for(int i = 1; i < end; i++) {
            File temp = new File(filePath + "/" +directories[i]);
            if (!temp.exists()) {
                System.out.println("=======" + "新建文件夹：" + directories[i] + "======");
                boolean result = temp.mkdirs();
                System.out.println("=======" + "新建文件夹成功：" + directories[i] + "======");
            }
            filePath.append("/");
            filePath.append(directories[i]);
        }
        File file = new File(path);
        System.out.println(file.exists());
    }

    public static void train2_1() throws IOException {
        // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
        byte data[] = { 88, 89 };
        // 准备文件lol2.txt其中的内容是空的
        File f = new File("d:/xyz/abc/def/lol2.txt");
        try {
            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f); // 这一步会创建文件

        } catch (IOException e) {
            File fs = f.getParentFile();
            System.out.println(fs);
            fs.mkdirs();
        }
        finally {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            // 关闭输出流
            fos.close();
        }
    }

    public static void train3() {
        String path = "d:/xyz/abc/def/WinNTSetup.exe";

        try {
            //准备文件lol.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File(path);
            //创建基于文件的输入流
            FileInputStream fis =new FileInputStream(f);

            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[100*1024]; // 100K
            //以字节流的形式读取文件所有内容

            int i = 0;
            while (fis.read(all) != -1) {

                String newFilePath = path + i;
                FileOutputStream fos = new FileOutputStream(newFilePath);

                fos.write(all); // 写数据
                System.out.println("已创建新的文件:"+newFilePath);
                fos.close();
                i++;
            }

            //每次使用完流，都应该进行关闭
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void train4() {
        String path = "d:/xyz/abc/def/WinNTSetup.exe";

        try {
            //准备文件lol.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File(path);
            //创建基于文件的输入流
            FileOutputStream fos =new FileOutputStream(f);

            //以字节流的形式读取文件所有内容
            int times = 34;
            for(int i=0; i<times ; i++) {
                File temp =  new File(path+i);
                //创建字节数组，其长度就是文件的长度
                byte[] all =new byte[(int) temp.length()]; // 100K
                FileInputStream fis = new FileInputStream(temp);
                fis.read(all);
                fos.write(all);
                fis.close();
            }


            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        test1();
//        train1();
//        test2();
//        test3();
//        train2();
//        train2_1();
//        train3();
        train4();
    }


}
