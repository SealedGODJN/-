package com.NPU.分布式实验;

import java.io.File;
import java.util.Scanner;

public class test {

    public static String root = "D:/http";

    public static void main(String[] args) {
        String name = "css.html";
        File f = new File(root, name);
        Scanner sc = new Scanner(System.in);
        String name1 = sc.nextLine();
        System.out.println(name1);
    }
}
