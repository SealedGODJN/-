package com.NPU.面向对象设计.chapter2;

import java.sql.Date;

/*
 * 普通雇员类，该类雇员每月拿固定的工资
 * @author machunyan
 */
public class GeneralEmployee extends Employee {

    protected double fixMonthSalary; //普通雇员的固定月薪

    /**
     * 初始化雇员基本信息的构造函数
     *
     * @param initId            雇员的唯一身份标识
     * @param initName          雇员的名字
     * @param initBirthday      雇员的出生日期
     * @param initMobileTel     雇员的联系方式
     * @param initMonthlySalary 普通雇员的月薪
     */
    public GeneralEmployee(String initId, String initName, Date initBirthday,
                           String initMobileTel, double initMonthlySalary) {

        super(initId, initName, initBirthday, initMobileTel);
        fixMonthSalary = initMonthlySalary;
    }

    /**
     * 获得雇员的固定月薪
     */
    public double getFixMonthSalary() {

        return fixMonthSalary;
    }

    /**
     * 获得雇员的每月的薪水
     */
    public double getMonthSalary(Date day) {

        return fixMonthSalary;
    }
}
