package com.how2j.java.IO.Stream.ObjectOutputStream;

import java.io.Serializable;

public class Hero implements Serializable {
    private static final long serialVersionUID = 1L; // 表示这个类的当前版本，有了变化，则应该修改这个版本号
    public String name;
    public float hp;
}
