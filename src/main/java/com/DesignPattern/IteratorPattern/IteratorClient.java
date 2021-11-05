package com.DesignPattern.IteratorPattern;

public class IteratorClient {
    public void checkAttendance() {
        Class c = new Class();
        System.out.println("--------------开始点名--------------");
        for (Student student : c) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        IteratorClient iteratorClient = new IteratorClient();
        iteratorClient.checkAttendance();
    }
}
