package com.java.Generic.improve;

public interface ListVisitor<T> {
    public T _case(Empty<T> that);

    public T _case(Cons<T> that);
}
