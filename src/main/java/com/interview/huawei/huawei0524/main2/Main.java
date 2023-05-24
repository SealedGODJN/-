package com.interview.huawei.huawei0524.main2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Queue<Pair> q10 = new LinkedList<>();
        Queue<Pair> q100 = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] tokens = line.split(":");
            int time = Integer.parseInt(tokens[0]);
            String log = tokens[1];

            while (!q10.isEmpty() && q10.peek().time + 10 < time) {
                set.remove(q10.peek().log);
                q10.poll();
            }

            String tmp = removeNum(log);
            while (!q100.isEmpty() && q100.peek().time + 100 < time) {
                String key = q100.peek().log;
                map.put(key, map.get(key) - 1);
                q100.poll();
            }

            if (set.contains(log)) continue;
            if (map.containsKey(tmp) && map.get(tmp) > 8) continue;

            set.add(log);
            if (map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp) + 1);
            } else {
                map.put(tmp, 1);
            }

            System.out.printf("%d:%s%n", time, log);
        }
    }

    private static String removeNum(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) continue;
            sb.append(c);
        }
        return sb.toString();
    }

    static class Pair {
        int time;
        String log;

        public Pair(int time, String log) {
            this.time = time;
            this.log = log;
        }
    }
}
