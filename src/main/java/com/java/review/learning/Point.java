package com.java.review.learning;

import java.math.BigDecimal;
 
public class Point {
 
    private BigDecimal x;
 
    private BigDecimal y;
 
    public Point (double y, double x) {
        this.x = new BigDecimal(x);
        this.y = new BigDecimal(y);
    }
 
    public Point (BigDecimal y, BigDecimal x) {
        this.x = x;
        this.y = y;
    }
 
    /**
     * 当前点和顶点之间构成的余弦值平方
     * 
     * @param p
     * @return
     */
    private BigDecimal cos2(Point p) {
        BigDecimal vector2 = (p.x.subtract(x).pow(2)).add(p.y.subtract(y).pow(2));
        return (p.x.subtract(x).pow(2)).divide(vector2, 11, BigDecimal.ROUND_HALF_DOWN);
    }
 
    /**
     * 当前点到顶点之间的Y向量差
     * 
     * @param p
     * @return
     */
    private BigDecimal toY(Point p) {
        return p.y.subtract(y);
    }
 
    /**
     * 当前点到顶点之间的x向量差
     * 
     * @param p
     * @return
     */
    private BigDecimal toX(Point p) {
        return p.x.subtract(x);
    }
 
    /**
     * 1度多少米
     * @return
     */
    private BigDecimal itude1() {
        return new BigDecimal(Math.cos(y.doubleValue())).multiply(new BigDecimal(111194.92474777778)).abs();
    }
 
    /**
     * 当前顶点到两点之间的距离
     * 
     * @param pb 起始点
     * @param pe 结束点
     * @return
     */
    public double distance(Point pb, Point pe) {
        if(pe.toX(pb).doubleValue() == 0) {
            BigDecimal dist2 = pe.toY(this).pow(2);
            return itude1().multiply(new BigDecimal(Math.sqrt(dist2.doubleValue()))).doubleValue();
        } else {
            BigDecimal vector = pe.toY(pb).multiply(toX(pb)).divide(pe.toX(pb), 11, BigDecimal.ROUND_HALF_DOWN).subtract(toY(pb));
            BigDecimal dist2 = pb.cos2(pe).multiply(vector.pow(2));
            return itude1().multiply(new BigDecimal(Math.sqrt(dist2.doubleValue()))).doubleValue();
        }
    } 
 
    public static void main(String[] args) {
        // 地图上画一个多边形
        Point[] points = {
                new Point(40.049409, 116.300804), 
                new Point(40.049409, 116.300904)
        };
        // 地图多边形内随机某一点
        Point p = new Point(41.049409, 116.300804);
 
        // 当前点到多边形各边的距离
        for (int i = 0; i < points.length; i++) {
            System.out.println("distance=" + p.distance(points[i], points[i == points.length - 1 ? 0 : i+1]) + "米");
        }
    }
}