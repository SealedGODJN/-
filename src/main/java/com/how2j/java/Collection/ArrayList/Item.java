package com.how2j.java.Collection.ArrayList;

public class Item {
    String name;
    int price;

    public Item(){

    }

    //提供一个初始化name的构造方法
    public Item(String name){
        this.name = name;
    }

    public void effect(){
        System.out.println("物品使用后，可以有效果");
    }

}
