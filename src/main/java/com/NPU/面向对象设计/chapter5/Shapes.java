package com.NPU.面向对象设计.chapter5;

/**
 *建模形状的类
 */
class Shape {
    /**
     *绘制
     */
    void draw() { }
    /**
     *擦除
     */
    void erase() { }
}
/**
 *建模圆的类
 */
class Circle extends Shape {
    /**
     *重写基类中绘制方法
     */
    void draw() {
        System.out.println("Circle.draw()");
    }
    /**
     *重写基类中擦除方法
     */
    void erase() {
        System.out.println("Circle.erase()");
    }
}
/**
 *建模矩形的类
 */
class Square extends Shape {
    /**
     *重写基类中绘制方法
     */
    void draw() {
        System.out.println("Square.draw()");
    }
    /**
     *重写基类中擦除方法
     */
    void erase() {
        System.out.println("Square.erase()");
    }
}
/**
 *建模三角形的类
 */
class Triangle extends Shape {
    /**
     *重写基类中绘制方法
     */
    void draw() {
        System.out.println("Triangle.draw()");
    }
    /**
     *重写基类中擦除方法
     */
    void erase() {
        System.out.println("Triangle.erase()");
    }
}
public class Shapes {
    /**
     *随机创建Shape对象
     */
    public static Shape randShape() {
        switch((int)(Math.random() * 3)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
        }
    }
    public static void main(String[] args) {
        //声明Shape类型的数组对象并初始化
        Shape[] s = new Shape[9];
        // 初始化数组元素
        for(int i = 0; i < s.length; i++){
            s[i] = randShape();
            System.out.println(s[i]);
        }

        //多态方法调用的演示
        for(int i = 0; i < s.length; i++){
            s[i].draw();
        }
    }
}
