package com.how2j.java.IO.Stream.ObjectOutputStream;

import java.io.*;

public class TestStream {
    public static void test1() {
        //创建一个Hero garen
        //要把Hero对象直接保存在文件上，务必让Hero类实现Serializable接口
        Hero h = new Hero();
        h.name = "garen";
        h.hp = 616;

        //准备一个文件用于保存该对象
        File f =new File("d:/garen.lol");

        try(
                //创建对象输出流
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos =new ObjectOutputStream(fos);
                //创建对象输入流
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois =new ObjectInputStream(fis);
        ) {
            oos.writeObject(h);
            Hero h2 = (Hero) ois.readObject();
            System.out.println(h2.name);
            System.out.println(h2.hp);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void train() {
        Hero[] heroes = new Hero[10];
        for (int i = 0; i < 10; i++) {
            Hero temp = new Hero();
            temp.name = "gareen" + i;
            temp.hp = i;
            heroes[i] = temp;
        }
        File f = new File("d:/hero.lol");

        try(
                //创建对象输出流
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos =new ObjectOutputStream(fos);
                //创建对象输入流
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois =new ObjectInputStream(fis);
        ) {
            for (int i = 0; i < 10; i++) {
                oos.writeObject(heroes[i]);
                Hero h2 = (Hero) ois.readObject();
                System.out.print(h2.name);
                System.out.print(" : ");
                System.out.println(h2.hp);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        test1();
        train();
    }
}
