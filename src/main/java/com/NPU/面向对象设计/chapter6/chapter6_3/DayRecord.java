package com.NPU.面向对象设计.chapter6.chapter6_3;

import java.sql.Date;

/*
 * 每日的工作记录，保存工作日期、工作时间等基本工作信息
 * @author author
 */
public class DayRecord {

    private Date workDay;            //工作日期
    private int hourCount;            //工作时间
    private static final String NEW_LINE = System.getProperty("line.separator");//系统的换行符

    /**
     * 初始化基本属性的构造函数
     *
     * @param workDay   特定的工作日期
     * @param hourCount 在特定日期中雇员工作的时间
     */

    public DayRecord(Date workDay, int hourCount) {

        super();
        this.workDay = workDay;
        this.hourCount = hourCount;
    }

    /**
     * 查看当前工作记录所在的日期
     */
    public Date getWorkDay() {

        return workDay;
    }

    /**
     * 查看当日的工作时间
     */
    public int getHourCount() {

        return hourCount;
    }

    /**
     * 返回此工作记录的字符串表示形式
     */

    public String toString() {

        return "worked " + hourCount + "hours in " + workDay + NEW_LINE;
    }
}
