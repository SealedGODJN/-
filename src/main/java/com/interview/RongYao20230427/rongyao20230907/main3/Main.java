package com.interview.RongYao20230427.rongyao20230907.main3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int power_max = Integer.MIN_VALUE, power = 0;

        int first = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(first);
        n--;
        while (n-- > 0) {
            int temp = scanner.nextInt();

            for (int v : list) {
                if (v > temp) {
                    power--;
                } else if (v < temp) {
                    power++;
                }
            }

            power_max = Math.max(power, power_max);

            list.add(temp);
        }

        System.out.println(power_max + " " + power);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            solve();
        }
    }
}