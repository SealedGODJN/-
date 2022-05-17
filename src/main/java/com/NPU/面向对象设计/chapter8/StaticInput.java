package com.NPU.面向对象设计.chapter8;

//引入I/O包

import java.io.*;

public class StaticInput {
    public static void main(String[] args) {
        char c;
        //标准输出流输出提示信息
        System.out.println("请输入用户信息：");
        try {
            //从标准输入流读取用户键盘输入
            c = (char) System.in.read();
            //获取标准输入流输入字节数
            int counter = System.in.available();
            for (int i = 1; i < counter + 1; i++) {
                //读取标准输入流输入字节，并通过标准输出流回显在屏幕
                System.out.println("第" + i + "个用户信息为" + c);
                c = (char) System.in.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
