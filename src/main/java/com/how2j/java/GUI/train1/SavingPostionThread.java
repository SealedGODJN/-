package com.how2j.java.GUI.train1;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SavingPostionThread extends Thread{
    private JFrame f;
    File file = new File("D:/location.txt");

    public SavingPostionThread(JFrame f) {
        this.f = f;
    }

    @Override
    public void run() {
        while (true) {
            int x = f.getX();
            int y = f.getY();
            try(
                BufferedWriter bw = new BufferedWriter(new FileWriter(file))
            ){
                bw.write(x + "\n");
                bw.write(y + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.printf("写入位置：%s,%s 成功", x, y);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
