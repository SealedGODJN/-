package com.NPU.deadlock;

import org.apache.poi.ss.formula.functions.T;

/**
 * 转账的时候遇到死锁，一旦打开注释，便会发生死锁
 */
public class TransferMoney implements Runnable {

    int flag = 1;

    static Account a = new Account(1, 500);
    static Account b = new Account(2, 500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney o1 = new TransferMoney();
        TransferMoney o2 = new TransferMoney();
        o1.flag = 1;
        o2.flag = 0;
        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        // 本人加锁
        synchronized (from) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("1 from.lock->" + from.name);
            // 对方加锁
            synchronized (to) {
//                System.out.println("2 to.lock->" + from.name);
                if (from.balance - amount < 0) {
                    System.out.println(from.name + "->" + to.name + "======余额不足，转账失败");
                    // throw
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println(from.name + "->" + to.name + "------成功转账" + amount + "元");
            }
        }
    }

    static class Account {
        int name;
        int balance;

        public Account(int name, int balance) {
            this.name = name;
            this.balance = balance;
        }
    }
}
