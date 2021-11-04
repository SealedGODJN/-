package com.DesignPattern.AdapterPattern;

//具体提供日志功能的实现类
public class NbLoggerImp implements NbLogger {
    @Override
    public void d(int priority, String message, Object... obj) {
        System.out.println(String.format("牛逼logger记录:%s",message));
    }
}