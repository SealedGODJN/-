package com.NPU.面向对象设计.chapter2;


import com.NPU.面向对象设计.chapter4.Person;

import java.util.Date;

/**
 * 建模雇员的类
 *
 * @author author name
 * @version 1.0.0
 */
public class Employee {

    /* 人的名字 */
    private String name;

    /* 人的住址 */
    private String address;

    /* 雇员生日 */
    private Date birthday;
    /* */
    private String mobileTel;

    public Employee(String name, String address, Date birthday, String mobileTel) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.mobileTel = mobileTel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }
}
