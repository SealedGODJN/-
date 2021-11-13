package com.java.thread.Multitasking;

/**
 * 使用实现Runnable接口
 */
public class TestMultitasking5{
    public static void main(String args[]){
        Runnable r1=new Runnable(){
            public void run(){
                System.out.println("task one");
            }
        };

        Runnable r2=new Runnable(){
            public void run(){
                System.out.println("task two");
            }
        };

        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);

        t1.start();
        t2.start();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/multitasking-in-multithreading.html

