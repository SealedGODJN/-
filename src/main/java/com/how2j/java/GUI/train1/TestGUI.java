package com.how2j.java.GUI.train1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestGUI {
    public static void main(String[] args) throws IOException {
        // 主窗体
        JFrame f = new JFrame("LoL");

        // 主窗体设置大小
        f.setSize(400, 300);

        // 主窗体设置位置
        Point p = getPointFromLocationFile();
        if(p!=null)
            f.setLocation(p.x,p.y);
        else
            f.setLocation(200, 200);

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
        f.setVisible(true);

        new SavingPostionThread(f).start();
    }

    private static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static Point getPointFromLocationFile() {
        String fileName = "D:/location.txt";
        int x = 0, y = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();
            if (line == null) return null;
            x = Integer.parseInt(line);

            line = br.readLine();
            if (line == null) return null;
            y = Integer.parseInt(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Point(x, y);
    }
}
