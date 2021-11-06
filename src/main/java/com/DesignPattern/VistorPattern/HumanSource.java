package com.DesignPattern.VistorPattern;

public class HumanSource implements CorporateSlave{
    private String name;

    public HumanSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(CorporateSlaveVisitor visitor) {
        visitor.visit(this); // visit方法的分派是由参数 this的运行时类型决定的
        // 通过这一步又完成了一次动态单分派。
        // 两次动态单分派结合起来就完成了一次伪动态双分派，为什么叫伪动态装派呢？
        // 因为在Java中动态分派是单分派的，而此处是通过两次动态单分派达到了双分派的效果，所以说是伪的！
    }
}
