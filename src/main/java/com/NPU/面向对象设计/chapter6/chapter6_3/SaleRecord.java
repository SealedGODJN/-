package com.NPU.面向对象设计.chapter6.chapter6_3;


import java.util.ArrayList;
import java.util.Iterator;

/*
 * 佣金雇员的销售记录，用于记录销售信息并计算薪水。
 * @author machunyan
 */
public class SaleRecord {

    private ArrayList<SaleItem> saleItems;

    /**
     * 构造一个空集合 {@link SaleItem}
     */
    public SaleRecord() {

        saleItems = new ArrayList<SaleItem>();
    }

    /**
     * 为当前销售记录添加新的销售项
     *
     * @param saleItem 将要被添加的新的工作记录
     */
    public void addSaleItem(SaleItem saleItem) {

        saleItems.add(saleItem);
    }

    /**
     * 返回迭代器遍历销售清单的销售项
     */
    public Iterator<SaleItem> iterator() {

        return saleItems.iterator();
    }

    /**
     * 按照给定产品名检索雇员的销售项
     *
     * @param productName 被检索的月中的某一个日期
     */
    public SaleItem getSaleItem(String productName) {

        for (SaleItem saleItem : saleItems) {
            if (productName.equals(saleItem.getProductName()))
                return saleItem;
        }
        return null;
    }

    /**
     * 获得雇员销售项的数量
     */
    public int getNumberOfSaleItem() {

        return saleItems.size();
    }

    /**
     * 返回此销售记录所涉及的销售总额
     */
    public double getSale() {

        double result = 0;
        for (SaleItem saleItem : saleItems) {
            result += saleItem.getPrice() * saleItem.getQuantity();
        }
        return result;
    }

    /**
     * 返回此销售记录的字符串表示形式
     */
    public String toString() {
        String result = "";
        for (SaleItem saleItem : saleItems) {
            result += "\t" + saleItem.toString();
        }
        result += "total sale : " + getSale() + "\n";
        return result;
    }
}