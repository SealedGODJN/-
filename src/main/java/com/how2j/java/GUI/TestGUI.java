package com.how2j.java.GUI;

import javax.swing.*;
import java.io.*;

public class TestGUI {

    public static void test1() throws IOException {
        // 主窗体
        JFrame f = new JFrame("LoL");

        // 主窗体设置大小
        f.setSize(400, 300);

        int[] recordLocation = readRecordLocation();
        if (recordLocation == null) {
            // 主窗体设置位置
            f.setLocation(200, 200);
        } else {
            int x,y;
            x = recordLocation[0];
            y = recordLocation[1];
            f.setLocation(x, y);
        }



        // 主窗体中的组件设置为绝对定位
        f.setLayout(null);

        // 按钮组件
        JButton b = new JButton("一键秒对方基地挂");

        // 同时设置组件的大小和位置
        b.setBounds(50, 50, 280, 30);

        // 把按钮加入到主窗体中
        f.add(b);

        // 关闭窗体的时候，退出程序
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        f.setVisible(true); //注意：f.setVisible(true); 会对所有的组件进行渲染，所以一定要放在最后面

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    recordCloseLocation(f.getX(), f.getY());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private static int[] readRecordLocation() throws IOException {
        String fileName = "D:/location.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = null;
        int[] result = new int[2];
        for (int i = 0; i < result.length; i++) {
            line = br.readLine();
            if (line == null) return null;
            result[i] = Integer.parseInt(line);
        }

        br.close();

        return result;
    }

    public static void recordCloseLocation(int x, int y) throws IOException {
        String fileName = "D:/location.txt";

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        bw.write(x + "\n");
        bw.write(y + "\n");

        System.out.printf("写入位置：%s,%s 成功", x, y);

        bw.close();

    }

    public static void main(String[] args) throws IOException {
        test1();
    }
}
