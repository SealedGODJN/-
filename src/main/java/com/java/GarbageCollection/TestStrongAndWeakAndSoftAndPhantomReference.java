package com.java.GarbageCollection;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class TestStrongAndWeakAndSoftAndPhantomReference {
    WeakReference<StringBuilder> reference = new WeakReference<>(new StringBuilder());
    SoftReference<StringBuilder> reference1 = new SoftReference<>(new StringBuilder());
//    PhantomReference<StringBuilder> reference2 = new PhantomReference<>(new StringBuilder());

}
