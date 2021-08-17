package com.how2j.java.multiplethread;

import com.how2j.bean.Hero;

public class TestThread {
    public static void main(String[] args) {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        while (!teemo.isDead()) gareen.attackhero(teemo); // 盖伦攻击提莫

        while (!leesin.isDead()) bh.attackhero(leesin); // 好运姐攻击盲僧
    }
}
