package com.how2j.java.GUI;

import javax.swing.*;
import java.io.*;

public class TestGUI {

    /**
     * 2021-11-5 完成GUI的第一个练习
     * @throws IOException
     */
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
                recordCloseLocation(f.getX(), f.getY());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private static int[] readRecordLocation() {
        String fileName = "D:/location.txt";
        int[] result = new int[2];
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            for (int i = 0; i < result.length; i++) {
                line = br.readLine();
                if (line == null) return null;
                result[i] = Integer.parseInt(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void recordCloseLocation(int x, int y) {
        String fileName = "D:/location.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            bw.write(x + "\n");
            bw.write(y + "\n");

            System.out.printf("写入位置：%s,%s 成功", x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        test1();
    }
}
