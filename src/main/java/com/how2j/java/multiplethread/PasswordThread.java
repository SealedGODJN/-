package com.how2j.java.multiplethread;

import java.util.ArrayList;
import java.util.List;

public class PasswordThread extends Thread{

    public String password;
    private boolean found = false;
    public List<String> passwords;

    public PasswordThread(String password, List<String> passwords) {
        this.password = password;
        this.passwords = passwords;
    }

    public void run() {
        char[] guessPassword = new char[password.length()];
        guessPassword(guessPassword, password);
    }

    public void guessPassword(char[] guessPassword, String password) {
        guessPassword(guessPassword, 0, password);
    }

    // 使用递归进行穷举
    public void guessPassword(char[] guessPassword, int index, String password) {
        if(found) return;
        for(short i = '0'; i<='z'; i++) {
            char c = (char)i;
            if(!isDigitOrLetter(c)) continue;
            guessPassword[index] = c;
            if(index != password.length() - 1) {
                guessPassword(guessPassword, index+1, password);
            } else { // 已经生成了n位密码
                String guessPasswd = String.valueOf(guessPassword);
                passwords.add(guessPasswd);
                if(password.equals(guessPasswd)) {
                    System.out.printf("找到了密码:%s\n", guessPasswd);
                    found = true;
                    return;
                } // 如果匹配不成功，则开始循环，密码从最后一位开始尝试
            }
        }
    }

    // 判断该字符c是否是数字或者字母
    public boolean isDigitOrLetter(char c) {
        if(c >= '0' && c <= '9') return true;
        if(c >= 'A' && c <= 'Z') return true;
        if(c >= 'a' && c <= 'z') return true;
        return false;
    }

}
