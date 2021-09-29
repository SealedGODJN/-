package com.how2j.java.interfaceAndExtend.Override;

public class Item {
    String name;
    int price;

    public void buy() {
        System.out.println("购买");
    }

    public void effect() {
        System.out.println("物品使用后，可以有效果");
    }

    public static void main(String[] args) {
        Item i = new Item();
        i.effect();

        LifePotion lifePotion = new LifePotion();
        lifePotion.effect();

        MagicPotion mp = new MagicPotion();
        mp.effect();
    }
}
