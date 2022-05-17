package com.NPU.面向对象设计.chapter8;

//引入I/O包

import java.io.*;

public class DataOutput {
    public static void main(String[] args) {
        try {
            DataOutputStream dataOut = new DataOutputStream(
                    new FileOutputStream(System.getProperty("user.dir") + "\\Data" + File.separator + "Data.txt"));
            String s1 = "id";
            String[] str = {"userid", "name", "salary", "password"};
            int[] members = {2, 33, 10000, 111111};
            char in = System.getProperty("line.separator").charAt(0);
            dataOut.writeChars(s1);
            dataOut.writeChar(in);
            for (int i = 0; i < members.length; i++) {
                dataOut.writeChars(str[i]);
                dataOut.writeChar(in);
                dataOut.writeBytes(String.valueOf(members[i]));
            }
            System.out.println("总共输出:" + dataOut.size() + "个字符");
            dataOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}