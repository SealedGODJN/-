package com.DesignPattern.FacadePattern;

/**
 * Facade Pattern 有时也翻译成面板模式，是一个使用频率极高的设计模式。思想非常简单，对外提供简单的交互接口，隐藏内部的复杂性。
 *
 * 这在现实世界实在是太常见了，只要世界发生了混乱，这个模式就会有用武之地。此种机会的精髓就是： 乱！
 * 一般是行业初期多家服务商各种竞争还谁都没有胜出的时候，等到了后期决出老大后你这个生意也就凉了，如果一直决不出，那就可以一直做。
 */
public class FacadeClient {
    public void printReport(){
        new ReportFacade().generateReport();
    }

    public static void main(String[] args) {
        FacadeClient facadeClient = new FacadeClient();
        facadeClient.printReport();
    }
}

