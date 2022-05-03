package com.java.Generic;

public class AddVisitor implements ListVisitor {
    private Integer zero = new Integer(0);

    public Object _case(Empty that) {
        return zero;
    }

    public Object _case(Cons that) {
        return new Integer(((Integer) that.first() + (Integer) that.rest().accept(this)));
    }
}
