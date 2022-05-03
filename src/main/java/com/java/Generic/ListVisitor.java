package com.java.Generic;

public interface ListVisitor {
    public Object _case(Empty that);

    public Object _case(Cons that);
}
