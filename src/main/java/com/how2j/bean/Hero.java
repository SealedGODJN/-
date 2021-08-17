package com.how2j.bean;

import java.io.Serializable;

public class Hero {
    public String name;
    public float hp;

    public int damage;

    public void attackhero(Hero h) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        h.hp -= damage;
        System.out.format("%s 正在攻击 %s， %s 的血变成了 %.0f\n", name, h.name, h.name, h.hp);

        if(h.isDead()) {
            System.out.println(h.name + "死了！");
        }
    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }
}
