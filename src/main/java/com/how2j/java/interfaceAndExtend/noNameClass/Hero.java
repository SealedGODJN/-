package com.how2j.java.interfaceAndExtend.noNameClass;

public abstract class Hero {
    String name; // 姓名
    float hp; // 血量
    float armor; // 护甲
    int moveSpeed; // 移动速度

    public abstract void attack();

    public static void main(String[] args) {
        ADHero adh = new ADHero();
        adh.attack();
        System.out.println(adh);

        /**
         * 匿名类
         */
        Hero h = new Hero() {
            @Override
            public void attack() {
                System.out.println("新的进攻手段");
            }
        };
        h.attack();

        System.out.println(h);

        //与匿名类的区别在于，本地类有了自定义的类名
        // 本地类和匿名类一样，直接声明在代码块里面，可以是主方法，for循环里等等地方
        class SomeHero extends Hero{
            public void attack() {
                System.out.println( name+ " 新的进攻手段");
            }
        }

        SomeHero h1  =new SomeHero();
        h1.name ="地卜师";
        h1.attack();

        //在匿名类中使用外部的局部变量，外部的局部变量必须修饰为final
        final int damage = 5;

        Hero h2 = new Hero(){
            public void attack() {
                System.out.printf("新的进攻手段，造成%d点伤害",damage );
            }
        };
        h2.attack();

        //在匿名类中使用外部的局部变量damage 必须修饰为final
        int damage1 = 5;

        //这里使用本地类AnonymousHero来模拟匿名类的隐藏属性机制

        //事实上的匿名类，会在匿名类里声明一个damage属性，并且使用构造方法初始化该属性的值
        //在attack中使用的damage，真正使用的是这个内部damage，而非外部damage

        //假设外部属性不需要声明为final
        //那么在attack中修改damage的值，就会被暗示为修改了外部变量damage的值

        //但是他们俩是不同的变量，是不可能修改外部变量damage的
        //所以为了避免产生误导，外部的damage必须声明为final,"看上去"就不能修改了
        class AnonymousHero extends Hero{
            int damage1;
            public AnonymousHero(int damage1){
                this.damage1 = damage1;
            }
            public void attack() {
                damage1 = 10;
                System.out.printf("新的进攻手段，造成%d点伤害\n",this.damage1 );
            }
        }

        Hero h3 = new AnonymousHero(damage);
        h3.attack();

        Item i = new Item() {
            @Override
            public void disposable() {
                System.out.println("已消耗");
            }
        };
        i.disposable();
    }
}
