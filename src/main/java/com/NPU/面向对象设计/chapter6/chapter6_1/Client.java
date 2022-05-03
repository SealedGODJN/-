package com.NPU.面向对象设计.chapter6.chapter6_1;

import java.util.*;

/**
 * 该类建模一个客户端类。 包含有以下信息
 * <ol>
 * <li>The name of the client</li>
 * <li>The bank accounts of the client</li>
 * </ol>
 */
public class Client implements Iterable<BankAccount> {
    /* Name of client */
    private String name;

    /* Collection of <code>BankAccounts</code> objects.*/
    private ArrayList<BankAccount> accounts;

    /**
     * 创建一个Client对象
     * 创建一个维护银行账户的空集合
     *
     * @param initialName the name of the client.
     */
    public Client(String initialName) {

        this.name = initialName;
        this.accounts = new ArrayList<BankAccount>();
    }

    /**
     * 返回accounts
     *
     * @return 所有的银行账号
     */
    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    /**
     * 返回客户的姓名
     *
     * @return the name of this client.
     */
    public String getName() {

        return this.name;
    }

    /**
     * 为客户新增一个银行账户
     *
     * @param bankAccount the {@link BankAccount} object.
     */
    public void addAccount(BankAccount bankAccount) {

        this.accounts.add(bankAccount);
    }

    /**
     * 返回一个客户银行账户的迭代器
     * <p>
     * return  an {@link Iterator} over the bank accounts of this
     * client.
     */
    public Iterator<BankAccount> iterator() {

        return this.accounts.iterator();
    }

    /**
     * 返回客户的银行账户总数
     *
     * @return the number of bank account of this client.
     */
    public int getNumberOfAccounts() {

        return this.accounts.size();
    }

}

