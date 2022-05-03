package com.java.Generic;

public class Cons extends List {
    private Object first;
    private List rest;

    Cons(Object _first, List _rest) {
        first = _first;
        rest = _rest;
    }

    public Object first() {
        return first;
    }

    public List rest() {
        return rest;
    }

    public Object accept(ListVisitor that) {
        return that._case(this);
    }
}
