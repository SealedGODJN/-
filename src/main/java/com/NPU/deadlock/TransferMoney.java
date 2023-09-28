package com.NPU.deadlock;

import org.apache.poi.ss.formula.functions.T;

/**
 * 转账的时候遇到死锁，一旦打开注释，便会发生死锁
 */
public class TransferMoney implements Runnable {

    int flag = 1;

    static Account a = new Account(1, 500);
    static Account b = new Account(2, 500);

    static Object lock = new Account(3, 500);

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
        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println(from.name + "->" + to.name + "======余额不足，转账失败");
                    // throw
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println(from.name + "->" + to.name + "------成功转账" + amount + "元");
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized (from) {
                // 对方加锁
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            // 新的锁
            synchronized (lock) {
                // 无论是to先，还是first先都可以
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
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
