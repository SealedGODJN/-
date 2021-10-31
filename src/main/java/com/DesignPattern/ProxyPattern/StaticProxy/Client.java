package com.DesignPattern.ProxyPattern.StaticProxy;

public class Client {
    public static void main(String[] args) {
        ProxyFactory.getProxy().submit("工资流水在此");
        ProxyFactory.getProxy().defend();

        // 使用这种代理模式有什么好处呢，我们为什么不直接让王二狗直接完成本次诉讼呢？现实中的情况比较复杂，但是我可以简单列出几条：
        // 1、代理律师就可以在提起诉讼等操作之前做一些校验工作，或者记录工作。
        // 2、例如二狗提供的资料，律师可以选择的移交给法庭而不是全部等等操作，就是说可以对代理的对做一些控制。
        // 3、例如二狗不能出席法庭，代理律师可以代为出席。。。

        // 代理律师可以将一些周边业务从核心类中剥离？
    }
}
