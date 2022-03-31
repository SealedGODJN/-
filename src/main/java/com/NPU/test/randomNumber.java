package com.NPU.test;

import java.util.Random;

public class randomNumber {
    public static void main(String[] args) {
        Random random = new Random();
        int result = random.nextInt(4);
        System.out.println(result);
    }
}
