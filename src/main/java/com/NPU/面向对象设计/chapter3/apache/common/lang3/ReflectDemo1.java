package com.NPU.面向对象设计.chapter3.apache.common.lang3;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo1 {
    public static void deleteStudent(String id) {
        // 调用函数"deleteStudent"
        EduManagement eduManagement = new EduManagement();

        // 原生写法
        Method deleteStudentMethod = null;
        try {
            deleteStudentMethod = eduManagement.getClass().getDeclaredMethod("deleteStudent", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        deleteStudentMethod.setAccessible(true); // 设置访问级别，如果private函数不设置则调用会报错
        try {
            deleteStudentMethod.invoke(eduManagement, id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // commons写法
        try {
            /*
            Object invokeMethod(final Object object, final boolean forceAccess,
                    final String methodName, Object... args)
            调用有参的方法，
            第一个参数是对象，
            第二个参数是任何修饰的方法都可以访问(默认、protected、private、public)，
            第三个是方法名，
            第四个参数是参数
             */
            MethodUtils.invokeMethod(eduManagement, true, "deleteStudent", id);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String id = "2021669911";
        deleteStudent(id);
    }
}
