package com.interview.RongYao20230427.main1;

import java.util.*;

public class Main {
    public static String reSort(String string) {
        List<Character> listStr = new ArrayList<>();
        for (char c : string.toCharArray()) {
            listStr.add(c);
        }
        List<Character> alphabet = new ArrayList<>();
        List<Character> digit = new ArrayList<>();
        for (Character t : listStr) {
            if (Character.isDigit(t)) {
                digit.add(t);
            } else {
                alphabet.add(t);
            }
        }
        Collections.sort(alphabet);
        Collections.sort(digit);
        List<Character> newListStr = new ArrayList<>();
        for (Character i : listStr) {
            if (Character.isDigit(i)) {
                newListStr.add(digit.remove(0));
            } else {
                newListStr.add(alphabet.remove(0));
            }
        }
        StringBuilder newString = new StringBuilder();
        for (Character c : newListStr) {
            newString.append(c);
        }
        return newString.toString();
    }

    public static List<String> getData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要输入的字符串个数：");
        int num = scanner.nextInt();
        scanner.nextLine();
        List<String> stringsInput = new ArrayList<>();
        for (int i = 0; i < num; i++) {
//            System.out.println("请输入第" + (i + 1) + "个字符串的长度：");
            String length = scanner.nextLine(); // 字符串的长度
//            System.out.println("请输入第" + (i + 1) + "个字符串：");
            String singleString = scanner.nextLine();
            stringsInput.add(singleString);
        }
        scanner.close();
        return stringsInput;
    }

    public static void main(String[] args) {
        List<String> arr = getData();
        List<String> sortedArr = new ArrayList<>();
        for (String t : arr) {
            sortedArr.add(reSort(t));
        }
        System.out.println("排序后的数字是：");
        int i = 0;
        for (String s : sortedArr) {
            i++;
            if (i != sortedArr.size())
                System.out.println(s);
            else
                System.out.print(s);
        }
    }
}
