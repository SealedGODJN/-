package com.java.Generic.improve;

public class Empty<T> extends List<T> {
    public T accept(ListVisitor<T> that) {
        return that._case(this);
    }
}
