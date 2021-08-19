package com.how2j.java.multiplethread;

import java.util.Date;
import java.util.List;

public class LogThread extends Thread{
    private boolean found = false;
    public List<String> passwords;

    public LogThread(List<String> passwords) {
        this.passwords = passwords;
        this.setDaemon(true);//把记日志的这个线程，设置为守护线程
    }

    public void run() {
        while (true) {
            while(passwords.isEmpty()){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Date date = new Date();
            if(!passwords.isEmpty()) {
                String temp = passwords.remove(0);
                System.out.printf("%s : 尝试使用%s匹配，匹配失败\n", date, temp);
            }
        }
    }
}
