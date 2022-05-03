package com.NPU.面向对象设计.chapter6.chapter6_3;

import com.NPU.面向对象设计.chapter2.Employee;

import java.util.*;
import java.sql.Date;

/*
 * 工时雇员类，该类雇员按其工作时间得到工资，每周末结算。
 * 如果某天其工作时间超过8小时，则超过部分按原工资的150%计算
 */
public class HourEmployee extends Employee {

    private double hourSalary; //工时雇员每小时的工资

    private ArrayList<WeekRecord> weekRecords; //工时雇员每周的工作记录

    /**
     * 初始化雇员基本信息的构造函数
     *
     * @param initId           雇员的唯一身份标识
     * @param initName         雇员的名字
     * @param initBirthday     雇员的出生日期
     * @param initMobileTel    雇员的联系方式
     * @param initHourlySalary 工时雇员每小时的工资
     */
    public HourEmployee(String initId, String initName, Date initBirthday,
                        String initMobileTel, double initHourlySalary) {

        super(initId, initName, initBirthday, initMobileTel);
        hourSalary = initHourlySalary;
        weekRecords = new ArrayList<WeekRecord>();
    }

    /**
     * 获得工时雇员每小时的工资
     */
    public double getHourSalary() {

        return hourSalary;
    }

    /**
     * 为当前雇员添加新的周工作记录
     *
     * @param weekRecord 将要被添加的新的工作记录
     */
    public void addWeekRecord(WeekRecord weekRecord) {

        weekRecords.add(weekRecord);
    }

    /**
     * 返回迭代器,以访问HourlyEmployee的周工作记录
     *
     * @return an {@link Iterator} of {@link DayRecord}
     */
    public Iterator<WeekRecord> iterator() {

        return weekRecords.iterator();

    }

    /**
     * 按照给定日期检索雇员的周工作记录，该周工作记录中包含给定的日期
     *
     * @param workDay 被检索的周中的某一个日期
     * @return 该雇员的目标日期所在的周的工作记录
     */
    public WeekRecord getWeekRecord(Date workDay) {

        for (WeekRecord weekRecord : weekRecords) {
            if (weekRecord.getDayRecord(workDay) != null)
                return weekRecord;
        }
        return null;
    }

    /**
     * 获得雇员周工作记录的数量
     */
    public int getNumberOfWeekRecord() {

        return weekRecords.size();
    }

    /**
     * 返回工时雇员当前可以领到的工资
     */
    public double getSalary(Date workDay) {

        double result = 0;
        //获得当前雇员目前所在周的工作记录
        WeekRecord weekRecord = getWeekRecord(workDay);
        for (DayRecord day : weekRecord)
            if (day.getHourCount() > 8) {
                result += 8 * hourSalary + (day.getHourCount() - 8) * 1.5
                        * hourSalary;
            } else {
                result += day.getHourCount() * hourSalary;
            }
        return result;
    }
}