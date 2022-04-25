package com.NPU.面向对象设计.chapter4;

/**
 * 该类建模一个人
 *
 * @author author name
 * @version 1.0.0
 */
public class Person {
    /* 人的名字 */
    private String name;

    /* 人的住址 */
    private String address;

    /**
     * 构造函数
     *
     * @param initialName    初始化人的名字
     * @param initialAddress 初始化人的住址
     */
    public Person(String initialName, String initialAddress) {

        name = initialName;
        address = initialAddress;
    }

    /**
     * 返回人的名字
     *
     * @return 人的名字
     */
    public String getName() {

        return name;
    }

    /**
     * 返回人的住址
     *
     * @return 人的住址
     */
    public String getAddress() {

        return address;
    }

    /**
     * 返回代表人的属性信息的字符串
     *
     * @return 代表人的属性信息的字符串
     */
    public String toString() {
        return "name： " + name + "_address：  " + address;
    }
}
