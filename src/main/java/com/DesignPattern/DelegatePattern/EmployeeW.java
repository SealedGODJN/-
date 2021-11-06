package com.DesignPattern.DelegatePattern;

public class EmployeeW implements Employee {
    @Override
    public void working(String command) {
        System.out.println("我接到委派【" + command + "】，要开始写需求了" );
    }
}