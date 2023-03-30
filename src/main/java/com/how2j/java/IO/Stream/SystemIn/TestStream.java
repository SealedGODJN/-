package com.how2j.java.IO.Stream.SystemIn;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class TestStream {
    public static void test1() {
        try (InputStream is = System.in;) {
            while (true) {
                // 敲入a,然后敲回车可以看到
                // 97 13 10
                // 97是a的ASCII码
                // 13 10分别对应回车换行
                int i = is.read();
                if(i==-1) break; // -1 退出
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {

        Scanner s = new Scanner(System.in);

        while(true){
            String line = s.nextLine();
            System.out.println(line);
        }
    }

    public static void test3() {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        System.out.println("第一个整数："+a);
        int b = s.nextInt();
        System.out.println("第二个整数："+b);
    }

    /*
public class @class@ {
    public @type@ @property@;
    public @class@() {
    }
    public void set@Uproperty@(@type@  @property@){
        this.@property@ = @property@;
    }

    public @type@  get@Uproperty@(){
        return this.@property@;
    }
}
 */

    public static String[] classTemplate = {
        "public class @class@ {",
        "   public @type@ @property@;",
        "   public @class@() {",
        "   }",
        "   public void set@Uproperty@(@type@  @property@){",
        "       this.@property@ = @property@;",
        "   }",
        "   public @type@  get@Uproperty@(){",
        "       return this.@property@;",
        "   }",
        "}"
    };

    /**
     * 自动创建有一个属性的类文件。
     * 通过控制台，获取类名，属性名称，属性类型，根据一个模板文件，自动创建这个类文件，并且为属性提供setter和getter
     */
    public static void train() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入类的名称：");
        String className = sc.nextLine();

        System.out.println("请输入属性的类型：");
        String classMemberType = sc.nextLine();

        System.out.println("请输入属性的名称：");
        String classMember = sc.nextLine();

        String Uproperty = classMember.substring(0,1);
        Uproperty =  Uproperty.toUpperCase(Locale.ROOT);
        Uproperty += classMember.substring(1);

        // 新建文件
        File f = new File("d:/"+ className +".java");

        try (FileWriter fw = new FileWriter(f);
             PrintWriter pw = new PrintWriter(fw);)
        {
            for (String s : classTemplate) {
                // 存在，再进行处理property
                if (s.contains("@")) {

                    if (s.contains("class")) {
                        s = s.replaceAll("@class@", className);

                    }
                    if (s.contains("type")) {
                        s = s.replaceAll("@type@", classMemberType);

                    }
                    if (s.contains("property")) {
                        s = s.replaceAll("@property@", classMember);

                    }
                    if (s.contains("Uproperty")) {
                        s = s.replaceAll("@Uproperty@", Uproperty);

                    }
                }
                System.out.println(s);
                s += "\n";
                pw.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件是常见的IO操作，设计如下方法，实现复制源文件srcFile到目标文件destFile
     * @param srcFile 源文件路径
     * @param destFile 目的文件路径
     */
    public static void copyFile(String srcFile, String destFile){
        File src = new File(srcFile);
        File dest = new File(destFile);
        if (!src.exists()) {
            System.err.println("源文件不存在");
            return;
        }
        if (!dest.exists()) {
            if (dest.getParentFile().mkdirs()) {
                // 新建文件夹成功
                System.out.println("新建目标文件夹成功");
            } else {
                System.err.println("新建目标文件夹失败");
                return;
            }
        }

        try (
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dest);
        )
        {
            byte[] readAll = new byte[(int)src.length()];
            if (fis.read(readAll) != -1) {
                System.out.println("读取源文件成功");
            }
            fos.write(readAll);
            System.out.println("写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件夹,实现如下方法，把源文件夹下所有的文件 复制到目标文件夹下(包括子文件夹)
     * @param srcFolder
     * @param destFolder
     */
    public static void copyFolder(String srcFolder, String destFolder){
        File src = new File(srcFolder);
        if (src.isDirectory()) {

        } else {
            System.err.println(srcFolder + "不是文件夹");
            return;
        }
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        train();
        String srcFile = "d:/lol.txt";
        String destFile = "d:/xyz/ab/bc/da/123.txt";
        copyFile(srcFile, destFile);

    }
}

