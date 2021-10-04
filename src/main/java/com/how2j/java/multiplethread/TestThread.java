package com.how2j.java.multiplethread;

import com.how2j.bean.Hero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author hjn
 * @date 2021-8-17
 */
public class TestThread {

    public static void testThreadCreate() {
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

    public static void testThreadJoin() {
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

        Thread t1= new Thread(() -> {
            //匿名类中用到外部的局部变量teemo，必须把teemo声明为final
            //但是在JDK7以后，就不是必须加final的了
            while(!teemo.isDead()){
                gareen.attackhero(teemo);
            }
        });
        t1.start();

        // 代码执行至此，一直是main线程
        try {
            t1.join(); // t1 加入到main线程中，只有t1线程结束，代码才能继续运行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2= new Thread(() -> {
            while(!leesin.isDead()){
                bh.attackhero(leesin);
            }
        });
        t2.start();
    }

    public static void testThreadSleep() {
        Thread t1 = new Thread(()->{
            int seconds = 0;
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("已经玩了LOL %d 秒\n", seconds++);
            }
        });
        t1.start();
    }

    public static void testThreadPriority(){
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616000;
        gareen.damage = 1;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300000;
        teemo.damage = 1;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500000;
        bh.damage = 1;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455000;
        leesin.damage = 1;

        Thread t1= new Thread(() -> {
            //匿名类中用到外部的局部变量teemo，必须把teemo声明为final
            //但是在JDK7以后，就不是必须加final的了
            while(!teemo.isDead()){
                gareen.attackhero(teemo);
            }
        });

        Thread t2= new Thread(() -> {
            while(!leesin.isDead()){
                bh.attackhero(leesin);
            }
        });

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }

    public static void testThreadYield() {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616000;
        gareen.damage = 1;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300000;
        teemo.damage = 1;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500000;
        bh.damage = 1;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455000;
        leesin.damage = 1;

        Thread t1= new Thread(() -> {
            //匿名类中用到外部的局部变量teemo，必须把teemo声明为final
            //但是在JDK7以后，就不是必须加final的了
            while(!teemo.isDead()){
                gareen.attackhero(teemo);
            }
        });

        Thread t2= new Thread(() -> {
            while(!leesin.isDead()){
                Thread.yield();
                bh.attackhero(leesin);
            }
        });

        t1.setPriority(5);
        t2.setPriority(5);
        t1.start();
        t2.start();
    }

    // 当一个进程里，所有的线程都是守护线程的时候，结束当前进程
    // 守护线程通常会被用来做日志，性能统计等工作。
    public static void testThreadDaemon() {
        Thread t1 = new Thread(()->{
            int seconds = 0;

            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("已经玩LOL %d 秒 \n", seconds++);
            }
        });
        t1.setDaemon(true);
        t1.start();
    }

    public static void testadugen() {
        while (true) {

            for(int i = 0; i<3; i++) {
                System.out.printf("波动拳发射第 %d 次\n",i+1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("开始充能5s");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个长度是n的随机字符串，随机字符有可能是数字，大写字母或者小写字母
     * @param n 随机字符串的长度
     * @return 随机字符串
     */
    public static String genRandPasswd(int n) {
        char[] CHAR = {'a', 'A', '0'};
        char[] PASSWORD = new char[n];
        // 'a' 97 'z' 122
        // 'A' 65 'Z' 90
        // '0' 48 '9' 57
//        long seed = 1232414515L;
        for(int i=0; i<n; i++) {
//            Random rand = new Random(seed++); // 伪随机数
            Random rand = new Random();
            int index = rand.nextInt(3); // 生成0～3之间的整数
            int letterRand = rand.nextInt(26); // 生成0～25之间的整数
            int numberRand = rand.nextInt(10); // 生成0～9之间的整数
            char temp = CHAR[index];
            if(temp == '0') {
                temp += numberRand;
            } else {
                temp += letterRand;
            }
            PASSWORD[i] = temp;
        }
        return String.valueOf(PASSWORD);
    }

    // 判断该字符c是否是数字或者字母
    public static boolean isDigitOrLetter(char c) {
        if(c >= '0' && c <= '9') return true;
        if(c >= 'A' && c <= 'Z') return true;
        if(c >= 'a' && c <= 'z') return true;
        return false;
    }

    public static void guessPassword(char[] guessPassword, String password) {
        guessPassword(guessPassword, 0, password);
    }

    private static boolean found = false;
    private static String tryPassord = "";
    // 使用递归进行穷举
    public static void guessPassword(char[] guessPassword, int index, String password) {
        if(found) return;
        for(short i = '0'; i<='z'; i++) {
            char c = (char)i;
            if(!isDigitOrLetter(c)) continue;
            guessPassword[index] = c;
            if(index != password.length() - 1) {
                guessPassword(guessPassword, index+1, password);
            } else { // 已经生成了n位密码
                String guessPasswd = String.valueOf(guessPassword);
                tryPassord = guessPasswd;
                if(password.equals(guessPasswd)) {
                    System.out.printf("找到了密码:%s\n", guessPasswd);
                    found = true;
                    return;
                }
            }
        }
    }

    public static void decodePassword(String password) {
        new Thread(()->{
            char[] guessPassword = new char[password.length()];
            guessPassword(guessPassword, password);
        }).start();
    }

    public static void decodePasswordLog() {
        Thread thread = new Thread(()->{
            while (true) {
                while(tryPassord.isEmpty()){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Date date = new Date();
                if(!tryPassord.isEmpty()) System.out.printf("%s : 尝试使用%s匹配，匹配失败\n", date, tryPassord);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
//        testThreadSleep();
//        testThreadJoin();
//        testThreadPriority();
//        testThreadYield();
//        testThreadDaemon();
//        testadugen();

        // 测试能否生成随机密码
//        String password = genRandPasswd(5);
//        System.out.println(password);
//        decodePassword(password);
//        decodePasswordLog();

        // 1、使用继承Thread类的方法创建新的线程
        String password = genRandPasswd(3);
        System.out.println(password);
        Thread.sleep(5000);
        List<String> tryPassword = new ArrayList<>();
        new PasswordThread(password, tryPassword).start();
        new LogThread(tryPassword).start();
    }

}
