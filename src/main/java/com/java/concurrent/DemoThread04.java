package com.java.concurrent;

public class DemoThread04 {
    private String name = "张三";
    private String address = "大兴";

    public synchronized void setVal(String name, String address) {
        this.name = name;
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.address = address;
        System.out.println("setValue最终结果:usename = " + name + ",password="+address);
    }

    public /*synchronized*/ void getVal() {
        System.out.println("getValue方法得到：username="+this.name + ", password="+ this.address);
    }

    public static void main(String[] args) throws Exception {
        final DemoThread04 dr = new DemoThread04();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setVal("李四", "昌平");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dr.getVal();
    }
}
