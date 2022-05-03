package com.java.Generic.improve;

public class Cons<T> extends List<T> {
    private T first;
    private List<T> rest;

    Cons(T _first, List<T> _rest) {
        first = _first;
        rest = _rest;
    }

    public T first() {
        return first;
    }

    public List<T> rest() {
        return rest;
    }

    public T accept(ListVisitor<T> that) {
        return that._case(this);
    }
}
