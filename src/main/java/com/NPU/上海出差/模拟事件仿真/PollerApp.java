package com.NPU.上海出差.模拟事件仿真;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PollerApp {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("delay.xml");
    }
}
