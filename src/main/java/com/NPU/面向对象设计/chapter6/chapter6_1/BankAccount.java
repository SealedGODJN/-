package com.NPU.面向对象设计.chapter6.chapter6_1;

/**
 * 该类建模一个银行的账户类
 */
public class BankAccount {
    /* Balance of the account*/
    private double balance;

    /**
     * 创建一个BankAccount对象，初始账户余额为0
     */
    public BankAccount() {
        this.balance = 0.0;
    }

    /**
     * 返回账户余额
     *
     * @return the balance of this account
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * 将钱存入账户。I如果指定金额为正数，则更新账户的余额
     *
     * @param amount amount of money to add to the balance.
     * @return true if the money is deposited;
     * false otherwise.
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;

            return true;
        } else {
            return false;
        }
    }

    /**
     * 从账户中取钱。如果指定金额为正数且账户有充足资金，则更新账户余额
     *
     * @param amount amount of money to subtract from the balance.
     * @return true if the money is withdrawn;
     * false  otherwise.
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}