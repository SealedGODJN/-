package com.java.Generic.improve;

/**
 * 可以尝试泛化AddVisitor
 */
public class AddVisitor implements ListVisitor<Integer> {
    private Integer zero = new Integer(0);

    public Integer _case(Empty<Integer> that) {
        return zero;
    }

    public Integer _case(Cons<Integer> that) {
        if (that.rest() == null) return (Integer) that.first();
        return new Integer(that.first() + that.rest().accept(this));
    }
}
