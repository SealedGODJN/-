package com.interview.RongYao20230427.main3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//工人个数
        Map<String, List<int[]>> map = new HashMap<>();//key-水果名，value-工人id和搬运重量
        List<String> fruits = new ArrayList<>();//记录水果的输入次序
        LinkedList<String> message = new LinkedList<>();

//        while (in.hasNext()) {
        for (int i = 0; i < n; i++) {
            message.offer(in.next());
            message.offer(in.next());
            message.offer(in.next());
        }

        while (!message.isEmpty()) {
            String fruitName = message.poll();//记录水果名
            int[] temp = new int[2];
            temp[0] = Integer.parseInt(message.poll());//记录工人id
            temp[1] = Integer.parseInt(message.poll());//记录工人搬运重量
            if (!fruits.contains(fruitName)) fruits.add(fruitName);
            if (!map.containsKey(fruitName)) {
                List<int[]> list = new ArrayList<>();
                list.add(temp);
                map.put(fruitName, list);
            } else {
                List<int[]> list = map.get(fruitName);
                list.add(temp);
                map.put(fruitName, list);
            }
        }
        for (String fruit : fruits) {
            List<int[]> list = map.get(fruit);
            Collections.sort(list, (a, b) -> {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            });
            for (int[] arr : list) {
                System.out.println(fruit + " " + arr[0] + " " + arr[1]);
            }
        }
    }
}