package com.java.Generic;

public class Empty extends List {
    public Object accept(ListVisitor that) {
        return that._case(this);
    }
}
