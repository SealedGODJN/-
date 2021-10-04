package com.how2j.java.String.MyStringBuffer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMyStringBuffer {
    @Test
    public void testNewMyStringBuffer() {
        MyStringBuffer mysb = new MyStringBuffer();
        assertEquals(mysb.value.length, 16);
    }

    @Test
    public void testAppendChar() {
        String str = "kindle";
        MyStringBuffer mysb = new MyStringBuffer(str);
        mysb.append('1');
        assertEquals("kindle1", mysb.toString());
    }

    @Test
    public void testAppendString() {
        String str = "123456";
        MyStringBuffer mysb = new MyStringBuffer(10);
        mysb.append(str);
        assertEquals("123456", mysb.toString());

        mysb.append(null);
        assertEquals("123456null", mysb.toString());
    }

    @Test
    public void testReverse() {
        String str = null;
        MyStringBuffer mysb = new MyStringBuffer(str);
        mysb.reverse();
        assertEquals("llun", mysb.toString());
    }
}
