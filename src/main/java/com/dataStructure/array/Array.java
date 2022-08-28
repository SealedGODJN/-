package com.dataStructure.array;

/**
 * 线性表-顺序表
 */
public class Array {

    private int[] data; // 定义一个基础数组，用来存放数据。
    private int size;  // 用记录数组中的数据个数。

    // 构造函数，实例化的时候需要指定一个容量capacity对数组进行初始化
    public Array(int capacity, int length) {
        data = new int[capacity];
        size = length;
    }

    // 无参构造，实例化时不指定容量将默认为10
    public Array() {
        this(10, 0);  // 调用有参构造函数
    }

    // 获取数组中元素的个数
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;  // 这里不可以直接return capacity，因为capacity不是成员变量。
    }

    // 判断当前数组是否为空
    // bool
    public boolean isEmpty() {
        return size == 0;
    }

    // 往第i个位置，插入一个元素e
    // 1 <= i <= data.length + 1
    public void insert(int i, int e) {
        // 判断i的位置是否在数组的元素存储范围内
        if (i < 1 || i > size + 1) {
            System.out.println("插入的位置超出数组的范围");
            return;
        }

        // 把i到data.length的所有元素都往后移一格
        for (int j = size; j >= i; j--) {
            data[j + 1] = data[j];
        }
        data[i - 1] = e;
        this.size++;
    }

    /**
     * 删除第i个位置的元素
     *
     * @param i
     */
    public void delete(int i) {
        if (i < 1 || i > size) {
            return;
        }

        for (int j = i; j < size; j++) {
            data[j - 1] = data[j];
        }
        this.size--;
    }

    public static void main(String[] args) {
        Array array = new Array();
        array.insert(1, 1);
        array.insert(2, 2);
        array.insert(3, 3);

        array.delete(1);
    }

}