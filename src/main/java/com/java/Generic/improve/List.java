package com.java.Generic.improve;

public abstract class List<T> {
    public abstract T accept(ListVisitor<T> that);
}
