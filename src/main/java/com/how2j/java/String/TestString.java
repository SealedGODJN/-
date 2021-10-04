package com.how2j.java.String;

import com.how2j.java.String.MyStringBuffer.MyStringBuffer;
import com.how2j.java.multiplethread.TestThread;

import java.util.ArrayList;

public class TestString {

    public static void testStringAndStringBuffer() {
        String str1 = TestThread.genRandPasswd(10);
        ArrayList<String> arrays = new ArrayList<>();
        for ( int i = 0; i < 10000 ; i ++ ) {
            String temp = TestThread.genRandPasswd(10);
            arrays.add(temp);
        }
        StringBuffer sb = new StringBuffer(str1);

        long startString = System.currentTimeMillis();

        /**
         * 能否转化为stream的写法？
         */
        for (String s :
                arrays) {
            str1 += s;
        }

        long endString = System.currentTimeMillis();

        long startStringBuffer = System.currentTimeMillis();

        for (String s :
                arrays) {
            sb.append(s);
        }
        long endStringBuffer = System.currentTimeMillis();

        System.out.println(endString-startString);
        System.out.println(endStringBuffer-startStringBuffer);
    }

    public static void testStringBufferUse() {
        String str1 = "let there ";
        StringBuffer sb = new StringBuffer(str1);

        sb.append("be right");
        System.out.println(sb);

        sb.delete(4, 10);
        System.out.println(sb);

        sb.insert(4, "there ");
        System.out.println(sb);

        sb.reverse(); // 反转
        System.out.println(sb);
    }

    public static void testStringAndMyStringBuffer() {
        StringBuilder str1 = new StringBuilder(TestThread.genRandPasswd(10));
        ArrayList<String> arrays = new ArrayList<>();
        for ( int i = 0; i < 1000000 ; i ++ ) {
            String temp = TestThread.genRandPasswd(10);
            arrays.add(temp);
        }
        MyStringBuffer mysb = new MyStringBuffer(str1.toString());

        long startString = System.currentTimeMillis();

        /**
         * 能否转化为stream的写法？
         */
        for (String s :
                arrays) {
            str1.append(s);
        }

        long endString = System.currentTimeMillis();

        long startStringBuffer = System.currentTimeMillis();

        for (String s :
                arrays) {
            mysb.append(s);
        }
        long endStringBuffer = System.currentTimeMillis();

        System.out.println(endString-startString);
        System.out.println(endStringBuffer-startStringBuffer);
    }

    public static void main(String[] args) {
//        testStringBufferUse();
//        testStringAndStringBuffer();
        testStringAndMyStringBuffer();
    }
}
