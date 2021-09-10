package com.how2j.java.classAndObject;

public class ADHero extends Hero {

    public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }

    // 可变数量的参数
    public void attack(Hero... heros) {
        for (int i = 0; i < heros.length; i++) {
            System.out.println(name + " 攻击了 " + heros[i].name);

        }
    }

    public static void main(String[] args) {
        ADHero bh = new ADHero();
        bh.name = "赏金猎人";

        Hero h1 = new Hero();
        h1.name = "盖伦";
        Hero h2 = new Hero();
        h2.name = "提莫";

        Hero h3 = new Hero();
        h3.name = "盲僧";

        bh.attack(h1);
        bh.attack(h1, h2);
        bh.attack(h1, h2, h3);

    }

}