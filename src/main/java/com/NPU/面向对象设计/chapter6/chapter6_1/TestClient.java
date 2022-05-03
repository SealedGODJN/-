package com.NPU.面向对象设计.chapter6.chapter6_1;

import java.util.*;
import java.io.*;

/**
 * 该类对客户类的实现进行测试.
 */
public class TestClient {

    /* Standard output stream */
    private static PrintWriter stdOut =
            new PrintWriter(System.out, true);

    /* Standard error stream */
    private static PrintWriter stdErr =
            new PrintWriter(System.err, true);

    /**
     * 对客户类的实现进行测试
     *
     * @param args not used.
     */
    public static void main(String[] args) {

        BankAccount accountOne = new BankAccount();
        BankAccount accountTwo = new BankAccount();
        BankAccount accountThree = new BankAccount();

        accountOne.deposit(1000.0);
        accountTwo.deposit(2000.0);
        accountThree.deposit(3000.0);

        Client client = new Client("John Smith");

        client.addAccount(accountOne);
        client.addAccount(accountTwo);
        client.addAccount(accountThree);

        double totalBalance = 0.0;

        for (BankAccount account : client.getAccounts()) {
            totalBalance += account.getBalance();
        }

        if (totalBalance != 6000.0) {
            stdErr.println("** Test failure");
        }
        stdOut.println("done");
    }
}