# helloworld

Just a repository 新增：数据结构+算法复习

## What is it?

这是一个idea intellij工程，原工程名为Test_2020_3_2，记录了笔试的准备和数据结构复习

## 里程碑

### 2021-10-04 实现了自己的StringBuffer

1、append的性能相比StringBuffer的append提升10倍以上

### 2022-9-21 JVM 垃圾回收

#### 1、基础概念

##### Heap的内容

###### Eden Space

###### Survivor1 Space

###### Survivor2 Space

###### Tenured/Old space

##### generic garbage collection principles built into the JVM

###### Minor GC

def:

Collecting garbage from Young space(Your Space = Eden Space + Survivor Space)

1、当JVM无法给new Object分配足够的空间时，将触发Minor GC，例如：Eden Space满了。（the higher the allocation rate, the more frequently Minor GC is
executed）

2、MinGC与典型的GC（Mark,Sweep and Compact）不同，它使用Mark and Copy来清理。

3、在MinGC的过程中，老年代->年轻代的reference被认为是事实上的GC Root，而在MinGC的标记阶段，年轻代->老年代的reference被忽略。

4、**all Minor GCs **do  **trigger stop-the-world pauses** , stopping the application threads. 如果大多数Eden
Space里面的对象被认为是垃圾，则它们会被直接删除，而不是复制到Tenured Space

###### Major GC vs Full GC

* Major GC is cleaning the Tenured space.
* [Full GC](https://plumbr.io/handbook/garbage-collection-in-java#minor-gc-major-gc-full-gc) is cleaning the entire Heap
  – both Young and Tenured spaces.

1、Major GC由Minor GC引起（因此，很难将MInor GC 和 Major GC区分开）

2、不用去关注是Major GC 还是Full GC，只需要看application的所有线程都被停止了（这种情况是Full GC），or 它能够与应用程序线程并发进行（这种情况是Major GC）

##### generational hypothesis分代假设

##### GC algorithm
