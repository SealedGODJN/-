//package com.NPU.考试0531.第一题debug;
//
//import java.nio.BufferOverflowException;
//
//public class ByteBuffer {
//    /**
//     * 字节数组
//     */
//    private byte[] storage;
//
//    /**
//     * 缓存容量
//     */
//    private int capacity;
//
//    /**
//     * 缓存大小
//     * 当前写入的位置
//     * 字节数组已使用的部分
//     * size = position - offset
//     */
//    private int size;
//
//    /**
//     * 偏移量
//     */
//    private int offset;
//
//    /**
//     * 当前写入的位置
//     */
//    private int position;
//
//    public ByteBuffer(int cap) {
//        storage = new byte[cap];
//        capacity = cap;
//        offset = 0;
//        position = 0;
//        size = 0;
//    }
//
//    /**
//     * 真正的index（要加上偏移量）
//     *
//     * @param i
//     * @return
//     */
//    protected int index(int i) {
//        return i + offset;
//    }
//
//    protected int nextPutIndex() {
//        // TODO: 之后要扩容！
//        if (position >= capacity) {
//            throw new IndexOutOfBoundsException();
//        }
//        return position++;
//    }
//
//    protected int nextPutIndex(int x) {
//        // TODO: 之后要扩容！
//        if (position >= capacity) {
//            throw new IndexOutOfBoundsException();
//        }
//        position += x;
//        return position - x;
//    }
//
//    /**
//     * 检查插入的位置i是否超出范围
//     *
//     * @param i
//     * @return
//     */
//    protected int checkIndex(int i) {
//        // TODO: 之后要扩容！
//        if ((i < 0) || (i >= capacity)) {
//            throw new IndexOutOfBoundsException();
//        }
//        return 0;
//    }
//
//    /**
//     * 写入一个INT8
//     *
//     * @param b 一个字节
//     */
//    public ByteBuffer appendByte(byte b) {
//        storage[index(nextPutIndex())] = b;
//        return this;
//    }
//
//    public ByteBuffer appendByteToIndex(int i, byte b) {
//        // TODO: 之后要扩容！
//        storage[index(checkIndex(i))] = b;
//        return this;
//    }
//
//    /**
//     * 按大端序插入c（char）
//     *
//     * @param c
//     * @return
//     */
//    public ByteBuffer appendChar(char c) {
//        // 让position+2
//        int firstIndex = index(nextPutIndex(2));
//        appendByteToIndex(firstIndex, char1(c));
//        appendByteToIndex(firstIndex + 1, char0(c));
//        return this;
//    }
//
//    private static byte char1(char c) {
//        return (byte) (c >> 8);
//    }
//
//    private static byte char0(char c) {
//        return (byte) (c);
//    }
//
//    /**
//     * 大端序写入i
//     *
//     * @param i
//     * @return
//     */
//    public ByteBuffer appendInt(int i) {
//        // 让position+4
//        int firstIndex = index(nextPutIndex(4));
//        appendByteToIndex(firstIndex, int3(i));
//        appendByteToIndex(firstIndex + 1, int0(i));
//        appendByteToIndex(firstIndex + 2, int1(i));
//        appendByteToIndex(firstIndex + 3, int2(i));
//        return this;
//    }
//
//    private static byte int3(int x) {
//        // 只取最低8位
//        return (byte) (x >> 24);
//    }
//
//    private static byte int2(int x) {
//        // 只取最低8位
//        return (byte) (x >> 16);
//    }
//
//    private static byte int1(int x) {
//        // 只取最低8位
//        return (byte) (x >> 8);
//    }
//
//    private static byte int0(int x) {
//        // 只取最低8位
//        return (byte) (x);
//    }
//
//    /**
//     * 大端序写入i
//     *
//     * @param i
//     * @return
//     */
//    public ByteBuffer appendLong(long i) {
//        // 让position+8
//        int firstIndex = index(nextPutIndex(8));
//        appendByteToIndex(firstIndex, long7(i));
//        appendByteToIndex(firstIndex + 1, long6(i));
//        appendByteToIndex(firstIndex + 2, long5(i));
//        appendByteToIndex(firstIndex + 3, long4(i));
//        appendByteToIndex(firstIndex + 4, long3(i));
//        appendByteToIndex(firstIndex + 5, long2(i));
//        appendByteToIndex(firstIndex + 6, long1(i));
//        appendByteToIndex(firstIndex + 7, long0(i));
//        return this;
//    }
//
//    private static byte long7(long x) {
//        // 只取最低8位
//        return (byte) (x >> 56);
//    }
//
//    private static byte long6(long x) {
//        // 只取最低8位
//        return (byte) (x >> 48);
//    }
//
//    private static byte long5(long x) {
//        // 只取最低8位
//        return (byte) (x >> 40);
//    }
//
//    private static byte long4(long x) {
//        // 只取最低8位
//        return (byte) (x >> 24);
//    }
//
//    private static byte long3(long x) {
//        // 只取最低8位
//        return (byte) (x >> 32);
//    }
//
//    private static byte long2(long x) {
//        // 只取最低8位
//        return (byte) (x >> 16);
//    }
//
//    private static byte long1(long x) {
//        // 只取最低8位
//        return (byte) (x >> 8);
//    }
//
//    private static byte long0(long x) {
//        // 只取最低8位
//        return (byte) (x);
//    }
//
//    /**
//     * 添加另一个ByteBuffer
//     *
//     * @param src
//     * @return
//     */
//    public ByteBuffer appendByteBuffer(ByteBuffer src) {
//        if (src == this) {
//            throw new IllegalArgumentException();
//        }
//        // position
//        int n = src.getPosition();
//        // TODO: 之后要扩容！
//        if (n >= remaining()) {
//            throw new BufferOverflowException();
//        }
//        byte[] srcStorage = src.getStorge();
//        for (int i = 0; i < n; i++) {
//            storage[index(nextPutIndex())] = srcStorage[i];
//        }
//        return null;
//    }
//
//    private byte[] getStorge() {
//        return storage;
//    }
//
//    public int remaining() {
//        return position - capacity;
//    }
//
//    public int getPosition() {
//        return position;
//    }
//
//    /**
//     * 读出一个8位的int
//     *
//     * @return
//     */
//    public byte readByte() {
//        // 最高位不用补0
//        return storage[nextOffset()];
//    }
//
//    /**
//     * 返回offset 并对offset进行控制
//     *
//     * @return
//     */
//    private int nextOffset() {
//        if (offset < 0 && offset > position) {
//            throw new IndexOutOfBoundsException();
//        }
//        return offset++;
//    }
//
//    /**
//     * 读出一个INT16
//     *
//     * @return
//     */
//    public char readChar() {
//        char c = 0;
//        // 最高位不用补0
//        c |= storage[nextOffset()] & 0xff;
//        c |= storage[nextOffset()] << 8;
//        return c;
//    }
//
//    /**
//     * 读出一个32
//     *
//     * @return
//     */
//    public int readInt() {
//        int i = 0;
//        // 最高位不用补0
//        i |= (storage[nextOffset()]) << 24;
//        i |= (storage[nextOffset()] & 0xff) << 16;
//        i |= (storage[nextOffset()] & 0xff) << 8;
//        i |= storage[nextOffset()] & 0xff;
//        return i;
//    }
//
//    /**
//     * 读出一个64
//     *
//     * @return
//     */
//    public long readLong() {
//        long l = 0;
//        // 最高位不用补0
//        l |= (long) (storage[nextOffset()]) << 56;
//        // 0xff 目的是在 | 操作时，低位对其，高位补0
//        l |= (long) (storage[nextOffset()] & 0xff) << 48;
//        l |= (long) (storage[nextOffset()] & 0xff) << 40;
//        l |= (long) (storage[nextOffset()] & 0xff) << 32;
//        l |= (long) (storage[nextOffset()] & 0xff) << 24;
//        l |= (storage[nextOffset()] & 0xff) << 16;
//        l |= (storage[nextOffset()] & 0xff) << 8;
//        l |= (storage[nextOffset()] & 0xff);
//        return l;
//    }
//
//    /**
//     * 读取时跳过len字节
//     *
//     * @param len
//     */
//    public void skip(int len) {
//        if (offset + len < 0 || offset + len >= position) {
//            throw new IndexOutOfBoundsException();
//        }
//        offset = len;
//    }
//
//    /**
//     * 读取时 offset回退len字节
//     *
//     * @param len
//     */
//    public void back(int len) {
//        if (offset - len < 0 || offset - len >= position) {
//            throw new IndexOutOfBoundsException();
//        }
//        offset += len;
//    }
//
//    public static void main(String[] args) {
//        byte b = Byte.MIN_VALUE;
//        char c = Character.MIN_VALUE;
//        int i = Integer.MIN_VALUE;
//        long l = Long.MIN_VALUE;
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(i);
//        System.out.println(l);
//
//        byte b1 = Byte.MAX_VALUE;
//        char c1 = Character.MAX_VALUE;
//        int i1 = Integer.MAX_VALUE;
//        long l1 = Long.MAX_VALUE;
//        System.out.println(b1);
//        System.out.println(c1);
//        System.out.println(i1);
//        System.out.println(l1);
//
//        ByteBuffer byteBuffer = new ByteBuffer(2000);
//        byteBuffer.appendByte(b);
//        byteBuffer.appendChar(c);
//        byteBuffer.appendInt(i);
//        byteBuffer.appendLong(l);
//
//        byteBuffer.appendByte(b1);
//        byteBuffer.appendChar(c1);
//        byteBuffer.appendInt(i1);
//        byteBuffer.appendLong(l1);
//
//        byte[] storge = byteBuffer.getStorge();
//        for (int j = 0; j < byteBuffer.position; j++) {
//            System.out.print(storge[j] + " ");
//        }
//        System.out.println();
//        int result = byteBuffer.readByte();
//        System.out.println(result);
//        int result1 = byteBuffer.readChar();
//        System.out.println(result1);
//        int result2 = byteBuffer.readInt();
//        System.out.println(result2);
//        long result3 = byteBuffer.readLong();
//        System.out.println(result3);
//
//        result = byteBuffer.readByte();
//        System.out.println(result);
//        result1 = byteBuffer.readChar();
//        System.out.println(result1);
//        result2 = byteBuffer.readInt();
//        System.out.println(result2);
//        result3 = byteBuffer.readLong();
//        System.out.println(result3);
//    }
//}