package com.how2j.java.interfaceAndExtend.Object;

import com.how2j.java.interfaceAndExtend.Override.LifePotion;
import com.how2j.java.interfaceAndExtend.Override.MagicPotion;

public class Item {
    String name;
    int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前对象正在被回收");
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item temp = (Item) o;
            return this.price==temp.price;
        }
        return false;
    }

    public static void main(String[] args) throws Throwable {
        Item i1 = new Item("多兰剑", 450);
        Item i2 = new Item("多兰戒", 450);
        i1.toString();
        System.out.println(i1.equals(i2));

        i1.finalize();
        i2.finalize();
    }
}
