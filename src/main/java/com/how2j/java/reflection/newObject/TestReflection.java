package com.how2j.java.reflection.newObject;

import com.how2j.bean.Hero;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TestReflection {
    public static void test1() {
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

    public static Hero getHero() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String file = "hero.config";
        String fileName = System.getProperty("user.dir") + "/src/main/java/com/how2j/java/reflection/newObject/" + file;

        // 初始化map对象，存储hero.config的配置
        Map<String, String> map = new HashMap<>(2);

        // 初始化文件读入对象
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            String line = "";
            do {
                line = fileReader.readLine();
                if (line == null) {
                    break;
                }
                String[] elements = line.split("=");
                // 向map中存入hero.config配置
                map.put(elements[0], elements[1]);

            } while (true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Class pClass;
        Constructor c;
        Hero object = null;
        for(String key : map.keySet()) {
            switch (key) {
                case "className" :
                    pClass = Class.forName(map.get(key));
                    c = pClass.getConstructor();
                    object = (Hero) c.newInstance();
                    break;
                case "objectName" :
                    if (object != null) {
                        object.name = map.get(key);
                    }
                    break;
                default:
                    System.err.println("don't exist this property");
                    return null;
            }
        }
        return object;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        test1();
        Hero object = getHero();
        System.out.println(object.name);
    }
}
