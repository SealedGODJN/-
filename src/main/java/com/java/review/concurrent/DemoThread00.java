package com.java.review.concurrent;

/**
 * @author hjn
 * @date 2021.5.7
 * @modify 20201.5.7
 */
public class DemoThread00 {

    /**
     * new两个线程，使用两个线程同时访问UserServlet方法
     * @param args
     */
    public static void main(String[] args) {
        final UserServlet us = new UserServlet(); // 内部类访问公有类变量，需要加final

        new Thread(new Runnable() {
            @Override
            public void run() {
                us.setPass("李四", "777777");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                us.setPass("王五", "888888");
            }
        }).start();
    }
/*
输出：
Thread-0-name=王五 pass=777777
Thread-1-name=王五 pass=888888
 */
}

/**
 * @author hjn
 * @date 2021.5.7
 * @modify 2021.5.7
 */
class UserServlet{
    private User user;
    public UserServlet() {
        user = new User("张三", "123456");
    }

    public void setPass(String name, String pass) {
        user.set(name, pass);
    }
}

/**
 * @author hjn
 * @date 2021.5.7
 * @modify 2021.5.7
 */
class User{
    private String name;
    private String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public void set(String name, String pass) {
        this.name = name; // 设置用户名
        try{
            Thread.sleep(5000); // 第一个线程在修改完name之后，等待了5s，同时，第二个线程也进来访问name，修改了name
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pass = pass; // 设置密码
        System.out.println(Thread.currentThread().getName() + "-name=" + this.name + " pass=" + this.pass);
    }
}
