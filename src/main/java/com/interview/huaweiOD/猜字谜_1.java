package com.interview.huaweiOD;

import java.util.*;

public class 猜字谜_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input1 = sc.nextLine();
        String input2 = sc.nextLine();

        String[] allQuestion = input1.split(" ");
        String[] allAnswer = input2.split(" ");

        // 解法1
//        StringBuilder result = solveString(allQuestion, allAnswer);
        // 解法2
        StringBuilder result = solveTreeSet(allQuestion, allAnswer);

        if (result.length() >= 1) {
            result.deleteCharAt(result.length() - 1);
            System.out.println(result);
        } else System.out.println("not found");

    }

    /**
     * 解法1：直接contains
     */
    public static StringBuilder solveString(String[] allQuestion, String[] allAnswer) {
        StringBuilder result = new StringBuilder();

        for (String question : allQuestion) {
            for (String answer : allAnswer) {
                boolean isAnswer = true;
                for (int i = 0; i < question.length(); i++) {
                    if (!answer.contains(question.substring(i, i + 1))) {
                        isAnswer = false;
                        break;
                    }
                }
                if (isAnswer) {
                    result.append(answer).append("，");
                }
            }
        }
        return result;
    }

    /**
     * 利用TreeSet解决【字母去重】【字母排序】
     */
    public static StringBuilder solveTreeSet(String[] allQuestion, String[] allAnswer) {
        StringBuilder result = new StringBuilder();

        for (String question : allQuestion) {
            TreeSet<Character> questionSet = new TreeSet<>();
            for (int i = 0; i < question.length(); i++) {
                questionSet.add(question.charAt(i));
            }
            for (String answer : allAnswer) {
                TreeSet<Character> answerSet = new TreeSet<>();
                for (int i = 0; i < answer.length(); i++) {
                    answerSet.add(answer.charAt(i));
                }
                if (questionSet.toString().equals(answerSet.toString())) {
                    result.append(answer).append(",");
                }
            }
        }
        return result;
    }
}
