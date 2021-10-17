package com.how2j.java.IO.Stream.DataStream;

import java.io.*;

public class TestStream {
    /**
     * 要用DataInputStream 读取一个文件，这个文件必须是由DataOutputStream 写出的，
     * 否则会出现EOFException，因为DataOutputStream 在写出的时候会做一些特殊标记，只有DataInputStream 才能成功的读取。
     *
     * 使用数据流的writeUTF()和readUTF() 可以进行数据的格式化顺序读写
     */
    public static void write() {
        File f = new File("d:/lol.txt");
        try(
                FileOutputStream fos = new FileOutputStream(f);
                DataOutputStream dos = new DataOutputStream(fos);
        ) {
            dos.writeBoolean(true);
            dos.writeInt(300);
            dos.writeUTF("123 this is gareen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        File f = new File("d:/lol.txt");
        try(
                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis);
        ) {
            boolean b = dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();

            System.out.println("读取到布尔值:"+b);
            System.out.println("读取到整数:"+i);
            System.out.println("读取到字符串:"+str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void train() {
        File f1 = new File("d:/lol3.txt");
        int a = 100, b = 200;

        try (
            FileWriter fos = new FileWriter(f1);
            BufferedWriter bw = new BufferedWriter(fos);
        )
        {
            bw.write(String.valueOf(a) + "@" + String.valueOf(b));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileReader fos = new FileReader(f1);
                BufferedReader bw = new BufferedReader(fos);
        )
        {
            String line;
            while((line = bw.readLine()) != null) {
                String[] lines = line.split("@");
                int a1 = Integer.parseInt(lines[0]);
                int b1 = Integer.parseInt(lines[1]);
                System.out.println(a1);
                System.out.println(b1);
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void train1() {
        File f1 = new File("d:/lol4.txt");
        int a = 200, b = 300;

        try (
                FileOutputStream fos = new FileOutputStream(f1);
                DataOutputStream bw = new DataOutputStream(fos);
        )
        {
            bw.writeUTF(String.valueOf(a) + "@" + String.valueOf(b));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileInputStream fos = new FileInputStream(f1);
                DataInputStream bw = new DataInputStream(fos);
        )
        {
            String line;
            line = bw.readUTF();
            String[] lines = line.split("@");
            int a1 = Integer.parseInt(lines[0]);
            int b1 = Integer.parseInt(lines[1]);
            System.out.println(a1);
            System.out.println(b1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        write();
//        read();
//        train();
        train1();
    }
}
