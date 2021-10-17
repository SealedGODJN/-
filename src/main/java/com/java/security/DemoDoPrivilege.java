package com.java.security;

import java.io.File;
import java.io.IOException;
import java.security.AccessControlException;

import com.java.security.FileUtil;

public class DemoDoPrivilege {

    public static void main(String[] args) {
        System.out.println("***************************************");
        System.out.println("I will show AccessControl functionality...");

        System.out.println("Preparation step : turn on system permission check...");
        // 打开系统安全权限检查开关
        System.setSecurityManager(new SecurityManager());
        System.out.println();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Create a new file named temp1.txt via privileged action ...");
        // 用特权访问方式在工程 A 执行文件路径中创建 temp1.txt 文件
        FileUtil.doPrivilegedAction("temp1.txt");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();

        System.out.println("/////////////////////////////////////////");
        System.out.println("Create a new file named temp2.txt via File ...");
        try {
            // 用普通文件操作方式在工程 A 执行文件路径中创建 temp2.txt 文件
            File fs = new File(
                    "D:\\IDEA_PROGECT\\lab03\\bin");
            fs.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AccessControlException e1) {
            e1.printStackTrace();
        }
        System.out.println("/////////////////////////////////////////");
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("create a new file named temp3.txt via FileUtil ...");
        // 直接调用普通接口方式在工程 A 执行文件路径中创建 temp3.txt 文件
        FileUtil.makeFile("temp3.txt");
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println("***************************************");
    }
}