package com.NPU.面向对象设计.chapter6.chapter6_3;

import com.NPU.面向对象设计.chapter2.GeneralEmployee;

import java.util.*;
import java.sql.Date;

/*
 * 普通雇员中的非佣金雇员类。非佣金雇员每月末结算薪水。
 * @author machunyan
 */
public class NonCommissionEmployee extends GeneralEmployee {

    private ArrayList<NCEMonthRecord> nCEMonthRecords; //非佣金雇员的月工作记录

    /**
     * 初始化雇员基本信息的构造函数
     *
     * @param initId            雇员的唯一身份标识
     * @param initName          雇员的名字
     * @param initBirthday      雇员的出生日期
     * @param initMobileTel     雇员的联系方式
     * @param initMonthlySalary 普通雇员每月的工资
     */
    public NonCommissionEmployee(String initId, String initName,
                                 Date initBirthday, String initMobileTel, double initMonthlySalary) {

        super(initId, initName, initBirthday, initMobileTel, initMonthlySalary);
        nCEMonthRecords = new ArrayList<NCEMonthRecord>();
    }

    /**
     * 为当前雇员添加新的月工作记录
     *
     * @param nCEMonthRecord 将要被添加的新的工作记录
     */
    public void addNCEMonthRecord(NCEMonthRecord nCEMonthRecord) {

        nCEMonthRecords.add(nCEMonthRecord);
    }

    /**
     * 返回迭代器访问WeekRecord中的DayRecord
     *
     * @return an {@link Iterator} of {@link DayRecord}
     */
    public Iterator<NCEMonthRecord> iterator() {

        return nCEMonthRecords.iterator();

    }

    /**
     * 按照给定日期检索雇员的月工作记录，该月工作记录中包含给定的日期
     *
     * @param workDay 被检索的月中的某一个日期
     * @return 该雇员的目标日期所在的月的工作记录
     */
    public NCEMonthRecord getNCEMonthRecord(Date workDay) {

        for (NCEMonthRecord nCEMonthRecord : nCEMonthRecords) {
            if (nCEMonthRecord.getDayRecord(workDay) != null)
                return nCEMonthRecord;
        }
        return null;
    }

    /**
     * 获得雇员月工作记录的数量
     */
    public int getNumberOfNCEMonthRecord() {

        return nCEMonthRecords.size();
    }

}