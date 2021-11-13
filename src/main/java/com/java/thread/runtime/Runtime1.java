package com.java.thread.runtime;

public class Runtime1{
    public static boolean isWindows() {
        return System.getProperties().getProperty("os.version").contains("windows");
    }

    public static void main(String args[])throws Exception{
        if (isWindows()) {
            Runtime.getRuntime().exec("notepad"); //will open a new notepad
        }
        else Runtime.getRuntime().exec("/usr/bin/touch /Users/hjn/Desktop/test.md");
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/java-runtime-class.html#article-start

