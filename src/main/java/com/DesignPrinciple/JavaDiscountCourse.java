package com.DesignPrinciple;

public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice() {
        return super.getPrice();
    }

    /**
     * 重写父类的getPrice方法，避免修改父类的getPrice方法
     * @return
     */
    public Double getPrice() {
        return super.getPrice() * 0.61;
    }
}
