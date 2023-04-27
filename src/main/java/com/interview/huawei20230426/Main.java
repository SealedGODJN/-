package com.interview.huawei20230426;

// We have imported the necessary tool classes.
// If you need to import additional packages or classes, please import here.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
        // please finish the function body here.
        // please define the JAVA output here. For example: System.out.println(s.nextInt());
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] letters = line.split(" ");
        List<String> result = new ArrayList<>();
        if (letters.length != 0) {
            for (int i = 0; i < letters.length; i++) {
                result.add(solve(letters[i]));
            }
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < result.size(); i++) {
                str.append(result.get(i));
                str.append(" ");
            }
            str.deleteCharAt(str.length() - 1);
            System.out.println(str.toString());
        }
    }

    private static String solve(String letter) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < letter.length(); i++) {
            switch (letter.charAt(i)) {
                case 'a':
                    s.append('A');
                    break;
                case 'e':
                    s.append('E');
                case 'i':
                    s.append('I');
                case 'o':
                    s.append('O');
                case 'u':
                    s.append('U');
                case 'A':
                    s.append('A');
                case 'E':
                    s.append('E');
                case 'I':
                    s.append('I');
                case 'O':
                    s.append('O');
                case 'U':
                    s.append('U');
                default:
                    s.append(letter.charAt(i) - 26);
            }
        }
        return s.toString();
    }
}
