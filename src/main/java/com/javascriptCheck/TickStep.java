package com.javascriptCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjn
 */
public class TickStep {
    /**
     * 参考https://github.com/d3/d3-array/blob/main/src/ticks.js#L46
     * 实现：获取坐标间隔的step
     * @param min 数据最小值
     * @param max 数据最大值
     * @param count 应该分成多少个坐标
     * @return 坐标间隔的step
     */
    public static double tickStep(double min, double max, double count) {
        // 7.07
        double e10 = Math.sqrt(50);
        // 3.16
        double e5 = Math.sqrt(10);
        // 1.41
        double e2 = Math.sqrt(2);

        // 计算原始的每一个坐标之间的间隔
        double step0 = Math.abs(max - min) / Math.max(0, count);
        // 为什么要用Math.floor?
        double step1 = Math.pow(10, Math.floor(Math.log10(step0)));
        // 为什么不是step1 - step0
        double error = step0 / step1;
        if (error >= e10) {
            step1 *= 10;
        } else if (error >= e5) {
            step1 *= 5;
        } else if (error >= e2) {
            step1 *= 2;
        } else if (error < 1) {
            step1 *= 1;
        }
        return step1;
    }

    /**
     * 相比《可视化入门》的算法，我的算法能够包括整个区间，不需要进行nice操作
     *
     * @param min 数据最小值
     * @param max 数据最大值
     * @param count 应该分成多少个坐标
     * @return 一个包含所有坐标的数组
     */
    public static List<Double> ticks(double min, double max, double count) {
        // 获取坐标间隔
        double step = tickStep(min, max, count);
        // 获取坐标起始值
        double start = Math.floor(min);
        // 获取坐标结束值
        double end = Math.ceil(max);
        List<Double> result = new ArrayList<>();
        result.add(start);
        // 每隔一个step，添加一个坐标
        while (start + step < end) {
            result.add(start += step);
        }
        result.add(end);
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(tickStep(0.1, 9.9, 6));
//        System.out.println(tickStep(0.1, 29.9, 6));

        List<Double> result = ticks(0.1 ,99.9, 6);
        for (Double aDouble : result) {
            System.out.println(aDouble);
        }
    }
}
