package com.java.GarbageCollection;

/**
 * 垃圾收集的好处
 * 它使java内存有效，因为垃圾收集器从堆内存中删除未引用的对象。它由垃圾收集器(JVM的一部分)自动完成，因此开发人员不需要额外的工作。
 *
 * 如何对象不被引用？
 * 有以下几个方法：
 *
 * 1. 通过归零引用：
 * Employee e=new Employee();
 * e=null;
 *
 * 2. 通过为另一个分配引用：
 * Employee e1=new Employee();
 * Employee e2=new Employee();
 * e1=e2;//now the first object referred by e1 is available for garbage collection
 *
 *
 * 3. 通过匿名对象：
 * new Employee();
 *
 * <br>
 * <br>
 * finalize()方法
 * 每次在对象被垃圾回收之前调用finalize()方法。 此方法可用于执行清理处理。 此方法在Object类中定义为：
 * protected void finalize(){}
 *
 * 注意 ：JVM的垃圾收集器仅收集由new关键字创建的对象。 因此，如果创建了没有new的任何对象，则可以使用finalize方法执行清理处理(销毁剩余对象)。65
 *
 *
 * gc()方法gc()方法用于调用垃圾收集器以执行清理处理。 gc()可在System和Runtime类中找到。
 * public static void gc(){}
 * Java
 *
 * 注意 ：垃圾收集由称为垃圾收集器(GC)的守护程序线程执行。 此对象在对象被垃圾回收之前调用finalize()方法。
 *
 * //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/garbage-collection.html
 */
public class TestGarbage1{
    /**
     * 当对象被垃圾回收之前,调用finalize()方法
     */
    public void finalize(){
        System.out.println("object is garbage collected");
    }
    public static void main(String args[]){
        TestGarbage1 s1 = new TestGarbage1();
        TestGarbage1 s2 = new TestGarbage1();
        s1=null;
        s2=null;
        System.gc();
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java_multithreading/garbage-collection.html
