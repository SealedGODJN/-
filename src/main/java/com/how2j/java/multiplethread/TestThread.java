package com.how2j.java.multiplethread;

import com.how2j.bean.Hero;

/**
 * @author hjn
 * @date 2021-8-17
 */
public class TestThread {
    public static void main(String[] args) {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        // 实例1
        // 不用多线程，顺序执行
//        while (!teemo.isDead()) gareen.attackhero(teemo); // 盖伦攻击提莫
//
//        while (!leesin.isDead()) bh.attackhero(leesin); // 好运姐攻击盲僧

        // 实例2
        // 多线程方式
//        KillThread killThread1 = new KillThread(gareen,teemo);
//        killThread1.start();
//        KillThread killThread2 = new KillThread(bh, leesin);
//        killThread2.start();

        // 实例3
        // implements Runnable
//        Battle battle = new Battle(gareen, teemo);
//        new Thread(battle).start();
//        Battle battle1 = new Battle(bh, leesin);
//        new Thread(battle1).start();

        // 实例4
        // 匿名类：lamba写法
//        new Thread(()->{
//            while (!teemo.isDead()) gareen.attackhero(teemo); // 盖伦攻击提莫
//        }).start();
//
//        new Thread(()->{
//            while (!leesin.isDead()) bh.attackhero(leesin); // 好运姐攻击盲僧
//        }).start();

        // 匿名类：常规写法
        Thread t1= new Thread(){
            public void run(){
                //匿名类中用到外部的局部变量teemo，必须把teemo声明为final
                //但是在JDK7以后，就不是必须加final的了
                while(!teemo.isDead()){
                    gareen.attackhero(teemo);
                }
            }
        };
        t1.start();
        Thread t2= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    bh.attackhero(leesin);
                }
            }
        };
        t2.start();
    }
}
