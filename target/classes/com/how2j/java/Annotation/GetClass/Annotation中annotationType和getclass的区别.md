



# Annotation中annotationType和getClass的区别



[TOC]



## 原问题介绍：

```java
//不科学的Js宅 2022/1/12 22:33:42
//老师我想请问个问题

//不科学的Js宅 2022/1/12 22:34:24
//在Java语言中，通过Class.forName(className:String)方法获得的Class对象是Class<?>

//不科学的Js宅 2022/1/12 22:34:42
//我怎么才能让它变成Class<? extends Annotation>

//不科学的Js宅 2022/1/12 22:35:02
package com.github.unscientificjszhai.unscientificcourseparser.core.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * 注解处理器。用于在编译期处理注解生成一个类扫描器。
 *
 * @author UnscientificJsZhai
 */
public class ParserBeanProcessor extends AbstractProcessor {

    private Messager messager;
    private Filer filer;

    private final ArrayList<Element> elements = new ArrayList<>();
    private final Class<? extends Annotation> annotationClass;

    public ParserBeanProcessor() throws ClassNotFoundException {
        Class<?> annotationClass = Class.forName("com.github.unscientificjszhai.unscientificcourseparser.core.parser.ParserBean");
        this.annotationClass = (Class<? extends Annotation>) annotationClass;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            FileGenerator.generateJavaFile(filer, elements);
        } else {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotationClass);
            for (Element element : elements) {
                messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName().toString());
                this.elements.add(element);
            }
        }
        return true
    }
}
```



## 背景介绍：

annotationType()的返回值为 Class<? extends Annotation>类型

```java
public Class<? extends Annotation> annotationType()
annotationType方法返回Class<?>泛型，其中?是Annotation的子类
```

而getClass()的返回值为Class<?>

> 获取类对象有3种方式
>
> 1. Class.forName("com.how2j.java.Annotation")
> 	2. DBUtil.class
> 		3. new DBUtil().getClass()
>
> 在一个JVM中，一种类，只会有一个类对象存在。所以以上三种方式取出来的类对象，都是一样的。
>
> **注：** 准确的讲是一个ClassLoader下，一种类，只会有一个类对象存在。通常一个JVM下，只会有一个ClassLoader。



## 概述

JDK中获取注解时，返回的都是Annotation类型，如下(截取自JDK源码)

```java
public <A extends Annotation> A getAnnotation(Class<A> annotationClass)
```

当获取到Annotation的实例后，可以通过getClass(从Object继承而来)和annotationType(Annotation接口中的方法)获取到相关的Class。下面阐述一下两者的区别，以下内容为自己的推断，并不是通过翻阅源码获得的结论。





## getClass与annotationType对比

### 测试代码：

```java
package com.how2j.java.Annotation.GetClass;

import com.how2j.java.Annotation.DBUtil;
import com.how2j.java.Annotation.JDBCConfig;

import java.lang.annotation.Annotation;

public class GetClassAndAnnotationType {
    public static void main(String[] args) throws ClassNotFoundException {
//        JDBCConfig a = JDBCConfig.class.getAnnotation(JDBCConfig.class);
//        JDBCConfig a = (JDBCConfig) new DBUtil();
//        JDBCConfig a = DBUtil.class.getAnnotation(JDBCConfig.class);
        JDBCConfig a = Class.forName("com.how2j.java.Annotation.DBUtil").getAnnotation(JDBCConfig.class);
        Class<? extends Annotation> b = a.annotationType();
        Class<?> c = a.getClass();
        JDBCConfig d = b.getAnnotation(JDBCConfig.class);
        JDBCConfig e = c.getAnnotation(JDBCConfig.class);
        System.out.println(d == e);
    }
}

```



### 测试结果：

![image-20220113213008704](../../Annotation/GetClass/Annotation%E4%B8%ADannotationType%E5%92%8Cgetclass%E7%9A%84%E5%8C%BA%E5%88%AB.assets/image-20220113213008704.png)



从图中可以看出：

1、**getClass()**获取到的是一个代理类，通过该代理类，可以获取到类上注解的属性

2、**annotationType()**获取到的是注解本身，**通过该接口可以实现获取注解上的注解**，比如：上面通过annotationType可以获取到DBUtil上的JDBCConfig注解信息



### 推论：

注解是一类特殊的接口，其定义了注解实例(通过**getAnnotation**获取的类上面的注解信息)能够拥有的属性，它本身也有Class属性。

**getAnnotation**方法获取到的注解实例有自己的类型，它是JDK利用代理技术实现了注解(注解是一类特殊的接口)的实现类，并不是注解类型本身



# 附录：

### DBUtil

```java
//package com.how2j.java.Annotation;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBUtil {
//    static String ip = "127.0.0.1";
//    static int port = 3306;
//    static String database = "test";
//    static String encoding = "UTF-8";
//    static String loginName = "root";
//    static String password = "admin";
//    static{
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
//        return DriverManager.getConnection(url, loginName, password);
//    }
//    public static void main(String[] args) throws SQLException {
//        System.out.println(getConnection());
//    }
//}

package com.how2j.java.Annotation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@JDBCConfig(ip = "127.0.0.1", database = "test", encoding = "UTF-8", loginName = "root", password = "admin")
public class DBUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, NoSuchMethodException, SecurityException {
        JDBCConfig config = DBUtil.class.getAnnotation(JDBCConfig.class);

        String ip = config.ip();
        int port = config.port();
        String database = config.database();
        String encoding = config.encoding();
        String loginName = config.loginName();
        String password = config.password();

        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, SQLException {
        Connection c = getConnection();
        System.out.println(c);
    }

}
```



### JDBCConfig

```java
package com.how2j.java.Annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author hjn
 */
@Target({METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JDBCConfig {
    String ip();
    int port() default 3306;
    String database();
    String encoding();
    String loginName();
    String password();
}

```

