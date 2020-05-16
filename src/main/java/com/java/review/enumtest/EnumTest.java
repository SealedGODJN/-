package com.java.review.enumtest;

import java.util.Vector;
import java.util.Enumeration;

/**
 * Created By hjn On 2020.5.16
 */
public class EnumTest {
    public static void main(String args[]) {
        Enumeration<String> days;
        Vector<String> dayNames = new Vector<String>();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();
        while (days.hasMoreElements()){
           System.out.println(days.nextElement()); 
        }
     }
}