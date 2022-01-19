package com.how2j.java.reflection.VisitProperty;

import com.how2j.bean.Hero;

import java.lang.reflect.Field;

public class TestReflection {
    /**
     * getField 只能获取public的，包括从父类继承来的字段。
     *
     * getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。
     * (注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
     */
    public static void test1() {
        Hero h =new Hero();
        //使用传统方式修改name的值为garen
        h.name = "garen";
        System.out.println(h.name);
        try {
            //获取类Hero的名字叫做name的字段
            Field f1= h.getClass().getDeclaredField("name");
            Field f2 = h.getClass().getDeclaredField("price");

            // 由于price是私有的，因此需要设置其访问控制权
            f2.setAccessible(true);

            //修改这个字段的值
            f1.set(h, "teemo");
            f2.set(h, 1000.0f);
            //打印被修改后的值
            System.out.println(h.name);
            System.out.println(h.getPrice());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
