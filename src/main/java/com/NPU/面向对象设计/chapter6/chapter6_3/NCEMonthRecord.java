package com.NPU.面向对象设计.chapter6.chapter6_3;

import java.util.*;
import java.sql.Date;

/*
 * 每月的工作记录，为非佣金雇员保存本月内所有的日工作记录
 * @author machunyan
 */
public class NCEMonthRecord {

    private ArrayList<DayRecord> dayRecords; //本月的所有日工作记录

    public NCEMonthRecord() {

        dayRecords = new ArrayList<DayRecord>();
    }

    /**
     * 为当前日工作记录添加新的日工作记录
     *
     * @param dayRecord 将要被添加的新的工作记录
     */
    public void addDayRecord(DayRecord dayRecord) {

        dayRecords.add(dayRecord);
    }

    /**
     * 返回迭代器访问NCEMonthRecord中的DayRecord
     *
     * @return an {@link Iterator} of {@link DayRecord}
     */
    public Iterator<DayRecord> iterator() {

        return dayRecords.iterator();

    }

    /**
     * 按照给定日期检索雇员的日工作记录
     *
     * @param workDay 被检索的月中的某一个日期
     */
    public DayRecord getDayRecord(Date workDay) {

        for (DayRecord dayRecord : dayRecords) {
            if (workDay.equals(dayRecord.getWorkDay()))
                return dayRecord;
        }
        return null;
    }

    /**
     * 获得雇员日工作记录的数量
     */
    public int getNumberOfDayRecord() {

        return dayRecords.size();
    }

    /**
     * 返回此工作记录的字符串表示形式
     */
    public String toString() {

        String result = "";
        for (DayRecord dayRecord : dayRecords) {
            result += "\t" + dayRecord.toString();
        }
        return result;
    }
}