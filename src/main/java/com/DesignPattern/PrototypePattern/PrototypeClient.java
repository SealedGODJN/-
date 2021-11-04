package com.DesignPattern.PrototypePattern;

import java.util.List;

/**
 * 首先要有一个可以克隆自己的接口，例如此处的Prototype
 * 其他对象都是可以基于已经构建出来的原型对象扩展的。
 */
public class PrototypeClient {
    public static void main(String[] args){
        //创建原型
        Report reportPrototype = new Report();
        //耗费资源的操作
        reportPrototype.loadData(); // 提前加载数据？

        //使用原型对象构建新的对象
        Report reportWithTitle = (Report) reportPrototype.copy();
        List<String> reportContent= reportWithTitle.getContents();
        reportContent.add(0,"《江城子·密州出猎》");
        reportContent.add(1,"----------------------------------------------------------");

        for (String s : reportContent) {
            System.out.println(s);
        }
    }
}
