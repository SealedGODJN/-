package com.how2j.java.digit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestNumber {
    public static void test1() {
        int i = 5;

        //把一个基本类型的变量,转换为Integer对象
        Integer it = new Integer(i);

        //自动转换就叫装箱
        Integer it2 = i;

        System.out.println(it instanceof Number);

        //把一个Integer对象，转换为一个基本类型的int
        //封装类型转换成基本类型
        int i2 = it.intValue();

        //自动转换就叫拆箱
        int i3 = it;


        //int的最大值
        System.out.println(Integer.MAX_VALUE); // Integer四个字节
        //int的最小值
        System.out.println(Integer.MIN_VALUE);
    }

    public static void train() {
        byte b = 1;
        Byte b1 = b;
        System.out.println(b1);

        short s = 1;
        Short s1 = s;
        System.out.println(s1); // short16位

        float f = 1;
        Float f1 = f;
        System.out.println(f1); //

        double d = 1.0;
        Double d1 = d;
        System.out.println(d1);

//        Integer i1 = b; // 不能自动装箱
        Integer i1 = new Integer(b);
        Integer i2 = (int) b; // 不能自动装箱，必须强制转换

        System.out.println("i1 i2:"+i1 + " " + i2);

        int i = 1;
        Byte b2 = (byte) i;

        System.out.println("b2:"+b2);

        System.out.println(Byte.MAX_VALUE); // 根据Byte的最大值，可以推算出Byte的长度为8位，一个字节
    }

    public static void test2() {
        int i = 5;

        //方法1
        String str = String.valueOf(i); // 本质上还是调用各种封装类的toString方法

        //方法2
        Integer it = i;
        String str2 = it.toString();
    }

    public static void train2() {
        double f = 3.14;
        String s = Double.toString(f);
        // String s = String.valueOf(f);
        double f1 = Double.parseDouble(s);

        try{
            String s1 = "3.1a4";
            double f2 = Double.parseDouble(s1);
            System.out.println(f2);
        }catch (NumberFormatException e) { // 为什么Exception能精准匹配到每一个子类呢？因为在函数中写了try-catch语句
            e.printStackTrace();
        }
    }

    public static void test3() {
        float i=3.14f;
        Float fl=i;
        String str1=fl.toString();
        System.out.println(str1);

        float a=Float.parseFloat(str1);
        System.out.println(a+1);
    }

    public static final String OUTPUT_FORMAT = "%32s";

    public static String printBinary(int number) {
        return printBinary(number, 8, "|");
    }

    public static String printBinary(int number, int blockSize, String separator) {

        // pad leading zero
        String pBinary = String
                .format(OUTPUT_FORMAT, Integer.toBinaryString(number))
                .replace(" ", "0");

        // split by blockSize
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < pBinary.length()) {
            result.add(pBinary.substring(index, Math.min(index + blockSize, pBinary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }

    public static void test4() {
        String STRING_FORMAT = "%-10s = %s";

        int number = 24;

        System.out.println("Positive Number");
        System.out.println(String.format(STRING_FORMAT, "Input " + number, printBinary(number)));
        System.out.println(String.format(STRING_FORMAT, number + " >> 2", printBinary(number >> 2)));
        System.out.println(String.format(STRING_FORMAT, number + " >>> 2", printBinary(number >>> 2)));

        int number2 = -19;

        System.out.println("\nNegative Number");
        System.out.println(String.format(STRING_FORMAT, "Input " + number2, printBinary(number2)));
        System.out.println(String.format(STRING_FORMAT, number2 + " >> 2", printBinary(number2 >> 2)));
        System.out.println(String.format(STRING_FORMAT, number2 + " >>> 2", printBinary(number2 >>> 2)));
    }


    public static void test5() {
        int max = 10000*1000;
        int count = 0 ;
        for (int i = 1; i <=max; i++) {
            if(isPrime(i)){
                count++;
            }
        }
        System.out.println("一千万以内的质数一共有 : " + count);

    }

    private static boolean isPrime(int i) {
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if (0==i%j)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        test1();
//        train();
//        test2();
//        train2();
//        test3();
//        test4();
        test5();
    }
}
