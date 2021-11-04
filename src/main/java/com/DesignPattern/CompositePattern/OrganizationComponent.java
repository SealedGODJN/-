package com.DesignPattern.CompositePattern;

/**
 * 组合模式有所谓的透明方式与安全方式，是对于使用者而言的
 * 透明方式将所有对外操作都放在Component，叶子节点也得提供这些接口，虽然它实际上不支持这些操作。
 * 而安全方式只将叶子节点与组合对象同时提供的操作放在Component。
 *
 * 为啥叫透明方式呢？因为用户使用的时候根本不管是叶子节点，还是组合对象，反正看到的接口都一样。这样就不安全了，因为万一这个对象是个叶子节点，
 * 假设你又使用了一个它不能提供的操作，例如add，就出问题了…
 *
 * 通用操作定义在Component中，根据使用方式不同，透明方式与安全方式，有一定的不同
 * 组合节点Composite不仅要继承Component，而且要持有一个Component的集合
 * 叶子对象只继承Component即可
 */
public abstract class OrganizationComponent {
    private String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void add(OrganizationComponent organization);

    public abstract OrganizationComponent getChild(String orgName);

    public abstract int getStaffCount();

    @Override
    public String toString() {
        return name;
    }
}

