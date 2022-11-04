package com.interview.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class HJ3 {
    public static void test1() {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        SortedSet<Integer> N = new TreeSet<>();
        while (sc.hasNext()) {
            N.add(sc.nextInt());
        }
        Iterator iterator = N.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] num = new boolean[1001];
        StringBuilder stringBuilder = new StringBuilder();
        String number = "";
        while ((number = br.readLine()) != null) {
            int length = Integer.parseInt(number);
            // 这一步出错：未设置读取的上限，应该根据length来约束循环的次数
            for (int i = 0; i < length; i++) {
                num[Integer.parseInt(br.readLine())] = true;
            }
            for (int i = 0; i < 1001; i++) {
                if (num[i]) {
                    stringBuilder.append(i).append("\n");
                }
            }
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }
}
