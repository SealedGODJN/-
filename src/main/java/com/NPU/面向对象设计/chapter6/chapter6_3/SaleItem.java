package com.NPU.面向对象设计.chapter6.chapter6_3;

import java.sql.Date;

/*
 * 佣金雇员的销售记录项，用于记录某一次销售情况。
 * @author machunyan
 */
public class SaleItem {

    private String productName;        //所销售的产品名称
    private double price;            //产品价格
    private int quantity;            //销售数量
    private Date saleDay;            //销售日期

    /**
     * 初始化销售记录的构造函数
     *
     * @param initProductName 所销售的产品名称
     * @param initPrice       产品价格
     * @param initQuantity    销售数量
     * @param initSaleDay     销售日期
     */
    public SaleItem(
            String initProductName,
            double initPrice,
            int initQuantity,
            Date initSaleDay) {

        productName = initProductName;
        price = initPrice;
        quantity = initQuantity;
        saleDay = initSaleDay;
    }

    /**
     * 获得所销售的产品名称
     */
    public String getProductName() {

        return productName;
    }

    /**
     * 返回所销售的产品的单价
     */
    public double getPrice() {

        return price;
    }

    /**
     * 返回该次出售的产品数量
     */
    public int getQuantity() {

        return quantity;
    }

    /**
     * 返回此次销售发生的日期
     */
    public Date getSaleDay() {

        return saleDay;
    }

    /**
     * 返回此销售项的字符串表示形式
     */
    public String toString() {

        return quantity + " " +
                productName + " sold in " +
                saleDay + "at the price of $ " +
                price + " each.";
    }
}