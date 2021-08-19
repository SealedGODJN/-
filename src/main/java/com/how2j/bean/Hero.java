package com.how2j.bean;

import java.io.Serializable;

public class Hero {
    public String name;
    public float hp;

    public int damage;

    public void attackhero(Hero h) {
        // 暂停时间去掉，线程会尽力去争取cpu资源
//        try{
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        h.hp -= damage;
        System.out.format("%s 正在攻击 %s， %s 的血变成了 %.0f\n", name, h.name, h.name, h.hp);

        if(h.isDead()) {
            System.out.println(h.name + "死了！");
        }
    }

    public boolean isDead() {
        return 0 >= hp;
    }

    public void recover() {
        this.hp++;
//        System.out.printf("恢复1---盖伦的血量为%.0f%n\n", this.hp);
    }

    public void hurt() {
        synchronized (this) {
            this.hp--;
        }
//        System.out.printf("减少1---盖伦的血量为%.0f%n\n", this.hp);
    }

}
