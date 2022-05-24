package com.NPU.面向对象设计.chapter3.apache.common.lang3;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class ReflectDemo {
    public static String getStudentId(Student student) {
        // 反射获取对象实例属性的值
        // 原生写法
        Field idField = null;
        try {
            idField = student.getClass().getDeclaredField("id");
        } catch (NoSuchFieldException e) { // 捕获异常
            e.printStackTrace();
        }
        idField.setAccessible(true); // 设置访问级别，如果private属性不设置则访问会报错
        try {
            String value = (String) idField.get(student); //
        } catch (IllegalAccessException e) { // 捕获异常
            e.printStackTrace();
        }

        String id = "";
        // commons写法
        try {
            id = (String) FieldUtils.readDeclaredField(student, "id", true);//456
        } catch (IllegalAccessException e) { // 捕获异常
            e.printStackTrace();
        }
        // 注：方法readDeclaredField()的只会在当前类实例上寻找，方法readField()在当前类上找不到则会递归向父类上一直查找。
        return id;
    }

    public static void main(String[] args) {
        Student student = new Student("学生", "2021991166");
        System.out.println(getStudentId(student));
    }
}
