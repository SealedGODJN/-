package com.how2j.java.Annotation.GetClass;

import com.how2j.java.Annotation.DBUtil;
import com.how2j.java.Annotation.JDBCConfig;

import java.lang.annotation.Annotation;

public class GetClassAndAnnotationType {
    public static void main(String[] args) throws ClassNotFoundException {
//        JDBCConfig a = JDBCConfig.class.getAnnotation(JDBCConfig.class);
//        JDBCConfig a = (JDBCConfig) new DBUtil();
//        JDBCConfig a = DBUtil.class.getAnnotation(JDBCConfig.class);
        JDBCConfig a = Class.forName("com.how2j.java.Annotation.DBUtil").getAnnotation(JDBCConfig.class);
        Class<? extends Annotation> b = a.annotationType();
        Class<?> c = a.getClass();
        JDBCConfig d = b.getAnnotation(JDBCConfig.class);
        JDBCConfig e = c.getAnnotation(JDBCConfig.class);
        System.out.println(d == e);
    }
}
