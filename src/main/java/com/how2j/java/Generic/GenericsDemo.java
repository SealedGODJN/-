package com.how2j.java.Generic;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;

public class GenericsDemo {
    @Test
    public void main() {
        new Token<Foo<String>>() {}.parseGenericInfo();
    }

    public static class Foo<T> {
        T data;
    }

    public static abstract class Token<T> {
        public void parseGenericInfo() {
            // GenericsDemo$Token<GenericsDemo$Foo<java.lang.String>>
            ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();

            // GenericsDemo$Foo<java.lang.String>
            ParameterizedType targetGenericClass = (ParameterizedType) genericSuperclass.getActualTypeArguments()[0];

            // class GenericsDemo$Foo
            Class targetClass = (Class) targetGenericClass.getRawType();

            for (int i = 0; i < targetGenericClass.getActualTypeArguments().length; i++) {
                System.out.println(targetClass.getTypeParameters()[i] + " -> " + targetGenericClass.getActualTypeArguments()[i]);
            }
        }
    }
}