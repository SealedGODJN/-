package com.java.Reflect.Array;

import java.lang.reflect.Array;

public class GfG {
// main method

    public static void main(String[] args)

    {
// Declaring and defining a byte array

        byte a[] = { 1, 2, 3, 4, 5 };

// Traversing the array

        for (int i = 0; i < 5; i++) {
// Array.getByte method

            byte x = Array.getByte(a, i);

// Printing the values

            System.out.print(x + " ");

        }
        System.out.println();

        String temp = new String(a, 0, a.length);
        // value = {char[5]}
        // 0 = '\u0001' 1
        // 1 = '\u0002' 2
        // 2 = '\u0003' 3
        // 3 = '\u0004' 4
        // 4 = '\u0005' 5
        System.out.println(temp);

    }

}