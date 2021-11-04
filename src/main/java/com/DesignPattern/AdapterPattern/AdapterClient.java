package com.DesignPattern.AdapterPattern;

public class AdapterClient {
    /**
     * 通过适配器客户端就可以很轻松的切换到新的日志系统了
     *
     * 优点：
     * 极大的增强了程序的可扩展性，通过此模式，你可以随意扩展程序的功能，但却不需要修改接口
     *
     * 思考：
     * 本质上是对接口的重写，那就是多态
     *
     * @param args
     */
    public static void main(String[] args) {
        LogFactory logFactory = new LogAdapter(new NbLoggerImp());
        logFactory.debug("Test","我将使用牛逼logger打印log");
    }
}
