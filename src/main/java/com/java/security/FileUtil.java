package com.java.security;

import java.io.File;
import java.io.IOException;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class FileUtil {
    // 工程 A 执行文件的路径
    private final static String FOLDER_PATH = "D:\\IDEA_PROGECT\\helloworld\\bin";

    public static void makeFile(String fileName) {
        try {
            // 尝试在工程 A 执行文件的路径中创建一个新文件
            File fs = new File(FOLDER_PATH + "\\" + fileName);
            fs.createNewFile();
        } catch (AccessControlException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doPrivilegedAction(final String fileName) {
        // 用特权访问方式创建文件
        AccessController.doPrivileged(new PrivilegedAction<String>() {
            @Override
            public String run() {
                makeFile(fileName);
                return null;
            }
        });
    }
}