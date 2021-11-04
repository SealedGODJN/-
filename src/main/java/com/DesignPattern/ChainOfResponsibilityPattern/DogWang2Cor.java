package com.DesignPattern.ChainOfResponsibilityPattern;

/**
 * 优点
 * 是命令发出者与执行者解耦。
 * 一个命令可以被多个处理器执行，例如各种框架中的拦截器
 * 缺点
 * 设计模式通病：类增多了。如果组链时候不合理，可能导致请求得不到执行，还有可能将链变成一个环，请求在里面循环，永远都完不了。
 *
 *
 * 注意千万不能将链搞成一个环（将最后一个处理的下一个handler设置为第一个），那样就无法结束了。
 */
public class DogWang2Cor {
    public void applyBudget() {
        GroupLeader groupLeader = new GroupLeader();

        Manager manager = new Manager();
        groupLeader.setNextHandler(manager);

        CFO cfo = new CFO();
        manager.setNextHandler(cfo);

        System.out.println(String.format("领导您好：由于开发需求，需要购买一台Mac笔记本电脑，预算为%d 望领导批准", 95000));
        if (groupLeader.handle(95000)) {
            System.out.println("谢谢领导");
        } else {
            System.out.println("巧妇难为无米之炊，只能划船了...");
        }
    }

    public static void main(String[] args) {
        DogWang2Cor dogWang2Cor = new DogWang2Cor();
        dogWang2Cor.applyBudget();
    }
}
