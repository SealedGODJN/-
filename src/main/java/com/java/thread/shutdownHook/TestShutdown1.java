package com.java.thread.shutdownHook;

class MyThread extends Thread{
    public void run(){
        System.out.println("shut down hook task completed..");
    }
}

/**
 * 当JVM正常或突然关闭时，关闭挂钩可用于执行清理资源或保存状态。 执行干净资源意味着关闭日志文件，发送一些警报或其他内容。
 * 因此，如果要在JVM关闭之前执行某些代码，请使用关闭挂钩(shutdown hook)。
 * JVM什么时候关闭？JVM在以下情况下关闭:
 *
 * 用户在命令提示符下按ctrl + c
 * 调用System.exit(int)方法
 * 用户注销计算机。
 * 用户关闭计算机等
 * //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/shutdownhook-thread.html
 */
public class TestShutdown1{
    public static void main(String[] args)throws Exception {

        Runtime r = Runtime.getRuntime(); //可以通过调用静态工厂方法getRuntime()来获取Runtime类的对象。
        r.addShutdownHook(new MyThread()); //Runtime类的addShutdownHook()方法用于向虚拟机注册线程。

        System.out.println("Now main sleeping... press ctrl+c to exit");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/shutdownhook-thread.html#article-start

