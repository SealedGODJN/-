package com.NPU.面向对象设计.chapter6.chapter6_3;

import com.NPU.面向对象设计.chapter2.GeneralEmployee;

import java.util.*;
import java.sql.Date;

/*
 * 普通雇员中的佣金雇员类。佣金雇员每月末结算薪水。
 * @author machunyan
 */
public class CommissionEmployee extends GeneralEmployee {

    private ArrayList<CEMonthRecord> cEMonthRecords; //佣金雇员的月工作记录

    /**
     * 初始化雇员基本信息的构造函数
     *
     * @param initId            雇员的唯一身份标识
     * @param initName          雇员的名字
     * @param initBirthday      雇员的出生日期
     * @param initMobileTel     雇员的联系方式
     * @param initMonthlySalary 普通雇员每月的工资
     */
    public CommissionEmployee(String initId, String initName,
                              Date initBirthday, String initMobileTel, double initMonthlySalary) {

        super(initId, initName, initBirthday, initMobileTel, initMonthlySalary);
        cEMonthRecords = new ArrayList<CEMonthRecord>();
    }

    /**
     * 为当前雇员添加新的月工作记录
     *
     * @param cEMonthRecord 将要被添加的新的工作记录
     */
    public void addCEMonthRecord(CEMonthRecord cEMonthRecord) {

        cEMonthRecords.add(cEMonthRecord);
    }

    /**
     * 返回迭代器访问WeekRecord中的DayRecord
     *
     * @return an {@link Iterator} of {@link DayRecord}
     */
    public Iterator<CEMonthRecord> iterator() {

        return cEMonthRecords.iterator();

    }

    /**
     * 按照给定日期检索雇员的月工作记录，该月工作记录中包含给定的日期
     *
     * @param workDay 被检索的月中的某一个日期
     * @return 该雇员的目标日期所在的月的工作记录
     */
    public CEMonthRecord getCEMonthRecord(Date workDay) {

        for (CEMonthRecord cEMonthRecord : cEMonthRecords) {
            if (cEMonthRecord.getDayRecord(workDay) != null)
                return cEMonthRecord;
        }
        return null;
    }

    /**
     * 获得雇员月工作记录的数量
     */
    public int getNumberOfCEMonthRecord() {

        return cEMonthRecords.size();
    }

    /**
     * 返回佣金雇员当前可以领到的工资
     */
    public double getMonthSalary(Date day) {

        double result = getFixMonthSalary();
        double sale;

        for (CEMonthRecord record : cEMonthRecords) {

            sale = record.getSaleRecord().getSale();
            if (sale > 200000) {
                //超过20万的部分按照超过部分的15%作为佣金
                result += (sale - 200000) * 0.15;
                //超过10万到20万之间的部分：
                result += 10000;
            } else if (sale > 100000) {
                //超过10万的部分按照超过部分的10%作为佣金
                result += (sale - 100000) * 0.1;
            }
        }
        return result;
    }
}