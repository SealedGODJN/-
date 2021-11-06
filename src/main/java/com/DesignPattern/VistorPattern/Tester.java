package com.DesignPattern.VistorPattern;

public class Tester implements CorporateSlave {

    private String name;

    public Tester(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(CorporateSlaveVisitor visitor) {
        visitor.visit(this);
    }
}
