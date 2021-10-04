package com.java.huaweiJavaTraining;

public class Test2_zuoye1 {

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        Animal animal = tiger;
        animal.eat();
        Tiger tiger1 = (Tiger) animal;
        Goat goat = (Goat) animal;
        goat.eat();
    }
}
class Animal {
    public void eat() {
        System.out.println("动物吃东西!");
    }
}
class Tiger extends Animal {
    @Override
    public void eat() {
        System.out.println("老虎吃肉!");
    }
}
class Goat extends Animal {
    @Override
    public void eat() {
        System.out.println("山羊吃草.");
    }
}