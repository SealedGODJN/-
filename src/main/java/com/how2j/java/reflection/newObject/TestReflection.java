package com.how2j.java.reflection.newObject;

import com.how2j.bean.Hero;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestReflection {
    public static void main(String[] args) {
        Hero h1 = new Hero();
        h1.name = "teemo";
        System.out.println(h1);

        try {
            String className = "com.how2j.bean.Hero";
            Class pClass = Class.forName(className); // 类对象
            Constructor c = pClass.getConstructor(); // 构造器对象
            Hero h2 = (Hero)c.newInstance();
            h2.name = "gareen";
            System.out.println(h2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
