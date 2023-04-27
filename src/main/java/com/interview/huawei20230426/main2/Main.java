package com.interview.huawei20230426.main2;


import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Task> resource;
    static int startID;
    static int endID;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String range = scanner.nextLine();
        String[] startEnd = range.split(" ");
        startID = Integer.parseInt(startEnd[0]);
        endID = Integer.parseInt(startEnd[1]);

        resource = new LinkedList<>();
        for (int i = 0; i <= endID - startID; i++) {
            Task task = new Task();
            task.ID = startID + i;
            task.isUse = false;
            resource.addLast(task);
        }
        String actionNum = scanner.nextLine();
        for (int i = 0; i < Integer.parseInt(actionNum); i++) {
            String action = scanner.nextLine();
            switch (action.charAt(0)) {
                case '1':
                    dynamic(action.charAt(2));
                    break;
                case '2':
                    point(action.charAt(2));
                    break;
                case '3':
                    release(action.charAt(2));
                    break;
            }
        }
        for (Task task : resource) {
            if (!task.isUse) {
                System.out.println(task.ID);
                return;
            }
        }
    }

    private static void release(char c) {
        int releaseID = c - 48;
        Task temp = null;
        for (Task task : resource) {
            // 如果被占用，不进行操作
            if (task.ID == releaseID && task.isUse) {
                temp = task;
                task.isUse = false;
                break;
            }
        }
        if (temp != null) {
            resource.remove(temp);
            resource.addLast(temp);
        }
    }

    private static void point(char c) {
        int pointToID = c - 48;
        for (Task task : resource) {
            // 如果被占用，不进行操作
            if (task.ID == pointToID && !task.isUse) {
                task.isUse = true;
                return;
            }
        }
    }

    private static void dynamic(char c) {
        int emptyNum = 0;
        for (Task task : resource) {
            if (!task.isUse) {
                emptyNum++;
            }
        }
        int require = c - 48;
        // ID不足时，不进行操作
        if (require <= emptyNum) {
            for (Task task : resource) {
                if (!task.isUse) {
                    task.isUse = true;
                    require--;
                }
                if (require == 0) return;
            }
        }
    }

    static class Task {
        public int ID;
        public boolean isUse;
    }
}
