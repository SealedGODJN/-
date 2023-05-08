package com.interview.huawei.huawei20230506.main1;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        sc.nextLine();
//        String[] strArr = sc.nextLine().trim().split("\\s+");
//        StringBuilder binaryStringBuilder = new StringBuilder();
//        for (String s : strArr) {
//            s = s.substring(2);
//            binaryStringBuilder.append(String.format("%16s", Integer.toBinaryString(Integer.parseInt(s, 16))).replace(" ", "0"));
//        }
//
//        String binaryString = binaryStringBuilder.toString().substring(0, N);
//        List<String> solutions = new ArrayList<>();
//        List<Integer> offsets = new ArrayList<>();
//
//        for (int offset = -N + 1; offset < N; offset++) {
//            StringBuilder shiftedString = new StringBuilder();
//            for (int i = 0; i < N; i++) {
//                int index = i + offset;
//                if (index >= 0 && index < N) {
//                    shiftedString.append(binaryString.charAt(index) == '1' ? '0' : '1');
//                } else {
//                    shiftedString.append('0');
//                }
//            }
//
//            if (!shiftedString.toString().equals(binaryString)) {
//                solutions.add(shiftedString.toString());
//                offsets.add(offset);
//            }
//        }
//
//        System.out.println(solutions.size());
//        for (int i = 0; i < solutions.size(); i++) {
//            System.out.println((offsets.get(i) > 0 ? "+" : "") + offsets.get(i));
//            System.out.println(solutions.get(i));
//        }
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        sc.nextLine();
//        String[] strArr = sc.nextLine().trim().split("\\s+");
//        StringBuilder binaryStringBuilder = new StringBuilder();
//        for (String s : strArr) {
//            s = s.substring(2);
//            binaryStringBuilder.append(String.format("%16s", Integer.toBinaryString(Integer.parseInt(s, 16))).replace(" ", "0"));
//        }
//
//        String binaryString = binaryStringBuilder.toString().substring(0, N);
//        List<String> solutions = new ArrayList<>();
//        List<Integer> offsets = new ArrayList<>();
//
//        for (int offset = -N + 1; offset < N; offset++) {
//            StringBuilder shiftedString = new StringBuilder();
//            for (int i = 0; i < N; i++) {
//                int index = i + offset;
//                if (index >= 0 && index < N) {
//                    shiftedString.append(binaryString.charAt(index) == '1' ? '0' : '1');
//                } else {
//                    shiftedString.append('0');
//                }
//            }
//
//            boolean isValid = true;
//            for (int i = 0; i < N; i++) {
//                if (binaryString.charAt(i) == '1' && shiftedString.charAt(i) == '1') {
//                    isValid = false;
//                    break;
//                }
//            }
//
//            if (isValid && !shiftedString.toString().equals(binaryString)) {
//                boolean canReplace = false;
//                for (int i = 0; i < offsets.size(); i++) {
//                    if (Math.abs(offset) < Math.abs(offsets.get(i))) {
//                        offsets.set(i, offset);
//                        solutions.set(i, shiftedString.toString());
//                        canReplace = true;
//                        break;
//                    }
//                }
//
//                if (!canReplace) {
//                    solutions.add(shiftedString.toString());
//                    offsets.add(offset);
//                }
//            }
//        }
//
//        System.out.println(solutions.size());
//        for (int i = 0; i < solutions.size(); i++) {
//            System.out.println((offsets.get(i) > 0 ? "+" : "") + offsets.get(i));
//            System.out.println(solutions.get(i));
//        }
//    }
//}

import java.util.*;

public class Main {
    static int n;
    static List<String> strategies = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine();
        String[] hexNumbers = scanner.nextLine().split(" ");

        StringBuilder binaryString = new StringBuilder();
        for (String hex : hexNumbers) {
            binaryString.append(String.format("%16s", Integer.toBinaryString(Integer.parseInt(hex.substring(2), 16))).replace(' ', '0'));
        }

        String validBits = binaryString.substring(0, n);
        List<String> result = findCompensationStrategies(validBits);

        System.out.println(result.size() / 2);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    private static List<String> findCompensationStrategies(String bitString) {

        int leftShift = 1;
        int rightShift = 1;

        while (leftShift <= bitString.length() || rightShift <= bitString.length()) {
            boolean foundRight = false;
            boolean foundLeft = false;

            // 右移
            if (rightShift <= bitString.length()) {
                for (int i = 0; i < n; i++) {
                    if (bitString.charAt(i) == '0') continue;
                    String rightShifted = "";
                    if (i == 0) rightShifted = '1' + bitString.substring(1);
                    else if (i < n - 1)
                        rightShifted = bitString.substring(0, i) + '1' + bitString.substring(i + 1);
                    else rightShifted = bitString.substring(0, i) + '1';
                    if (isValidShift(bitString, rightShifted, rightShift, true)) {
                        if (!strategies.contains("+" + rightShift)) {
                            strategies.add("+" + rightShift);
                            strategies.add(rightShifted);
                            foundLeft = true;
                        }
                    }
                }
            }

            // 左移
            if (leftShift <= bitString.length()) {
                for (int i = 0; i < n; i++) {
                    if (bitString.charAt(i) == '0') continue;
                    String rightShifted = "";
                    if (i == 0) rightShifted = '1' + bitString.substring(1);
                    else if (i < n - 1)
                        rightShifted = bitString.substring(0, i) + '1' + bitString.substring(i + 1);
                    else rightShifted = bitString.substring(0, i) + '1';
                    if (isValidShift(bitString, rightShifted, leftShift, false)) {
                        if (!strategies.contains("-" + leftShift)) {
                            strategies.add("-" + leftShift);
                            strategies.add(rightShifted);
                            foundLeft = true;
                        }
                    }
                }
            }

            if (foundRight || foundLeft) {
                break;
            }

            rightShift++;
            leftShift++;
        }

        return strategies;
    }

    private static boolean isValidShift(String original, String shifted, int shift, boolean right) {
        if (!right) shift *= -1;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == '0' && shifted.charAt(Math.abs((i + shift) % n)) == '0') {
                return false;
            }
        }
        return true;
    }
}

//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        String[] hexNumbers = scanner.nextLine().split(" ");
//
//        StringBuilder binaryString = new StringBuilder();
//        for (String hex : hexNumbers) {
//            binaryString.append(String.format("%16s", Integer.toBinaryString(Integer.parseInt(hex.substring(2), 16))).replace(' ', '0'));
//        }
//
//        String validBits = binaryString.toString().substring(0, n);
//        List<String> result = findCompensationStrategies(validBits);
//
//        System.out.println(result.size() / 2);
//        for (String line : result) {
//            System.out.println(line);
//        }
//    }
//
//    private static List<String> findCompensationStrategies(String bitString) {
//        List<String> strategies = new ArrayList<>();
//        int leftShift = 1;
//        int rightShift = 1;
//
//        while (leftShift <= bitString.length() || rightShift <= bitString.length()) {
//            boolean foundRight = false;
//            boolean foundLeft = false;
//
//            if (rightShift < bitString.length()) {
//                String rightShifted = bitString.substring(bitString.length() - rightShift + 1) + bitString.substring(0, bitString.length() - rightShift);
//                if (isValidShift(bitString, rightShifted)) {
//                    strategies.add("+" + rightShift);
//                    strategies.add(rightShifted);
//                    foundRight = true;
//                }
//            }
//
//            if (leftShift < bitString.length()) {
//                String leftShifted = bitString.substring(leftShift) + bitString.substring(leftShift + 1);
//                if (isValidShift(bitString, leftShifted)) {
//                    strategies.add("-" + leftShift);
//                    strategies.add(leftShifted);
//                    foundLeft = true;
//                }
//            }
//
//            if (foundRight || foundLeft) {
//                break;
//            }
//
//            rightShift++;
//            leftShift++;
//        }
//
//        return strategies;
//    }
//
//    private static boolean isValidShift(String original, String shifted) {
//        for (int i = 0; i < original.length(); i++) {
//            if (original.charAt(i) == '1' && shifted.charAt(i) == '1') {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static String repeatString(String s, int count) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < count; i++) {
//            sb.append(s);
//        }
//        return sb.toString();
//    }
//}