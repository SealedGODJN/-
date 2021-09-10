package com.how2j.java.classAndObject;

public class GiantDragon {
    private static GiantDragon instance = new GiantDragon();

    private GiantDragon() {

    }

    public static GiantDragon getInstance() {
        return instance;
    }
}
