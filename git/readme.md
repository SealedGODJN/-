# git 命令行原理深入理解

时间：2022.6.20-7.3

## git本质

object数据库

key: 文件的sha256值

value: 文件压缩过的内容

## 1.init

创建文件夹目录

.git

|___config

|___description

|___HEAD

|___hooks/

|___info/

|___objects/

    |___info/（空目录）

    |___pack/（空目录）

|___refs

## 2.object

```shell
git hash-object [-t <type>] [-w] [--path=<file>|--no-filters] [--stdin [--literally]] [--] <file>…

   -t <type>   

指定类型（默认值：“blob”）。

   -w   

实际上将对象写入对象数据库。
```

```shell
git cat-file (-t [--allow-unknown-type] | -s [--allow-unknown-type] | -e | -p | <type> | --textconv | --filters) [--path=<path>] <object>

  <object>   

要显示的对象的名称。有关拼写对象名称的更完整列表，请参阅 gitrevisions [7] 中的“指定修订”部分。

   -t   

显示由<object>标识的对象类型而不是内容。

   -s   

显示由<object>标识的对象大小，而不是内容。

   -p   

基于其类型 Pretty-print（漂亮地打印）<object>的内容。


```

### 如何实现hash-object命令？

#### 1、计算object的sha256值

##### 1）了解object内容的构成

blob + " " + 文件的size + “\u0000" + 文件内容

##### 2）了解sha256加密算法

#### 2、写入object文件

##### 1）设定Object文件的文件夹和文件名

sha256的值前两位设置为文件夹的名称

sh256从第三位开始的值设置为object文件的文件名

##### 2）压缩：compress/zlib

[zlib](https://zh.wikipedia.org/wiki/Zlib "Zlib")是DEFLATE算法的实现库，它的API同时支持gzip文件格式以及一个简化的数据流格式。zlib数据流格式、DEFLATE以及gzip文件格式均已被分别标准化为 [RFC 1950](https://tools.ietf.org/html/rfc1950)、[RFC 1951](https://tools.ietf.org/html/rfc1951)、[RFC 1952](https://tools.ietf.org/html/rfc1952)。

这里有2个概念：

1.zlib是gz系列压缩算法的实现库。

2.zlib库支持3种压缩算法，包括：zlib数据流格式（zlib自创的）、deflate、gzip格式。

deflate是什么呢？

> gzip的基础是[DEFLATE](https://zh.wikipedia.org/wiki/DEFLATE "DEFLATE")，DEFLATE是[LZ77](https://zh.wikipedia.org/wiki/LZ77%E4%B8%8ELZ78 "LZ77与LZ78")与[哈夫曼编码](https://zh.wikipedia.org/wiki/%E5%93%88%E5%A4%AB%E6%9B%BC%E7%BC%96%E7%A0%81 "哈夫曼编码")的一个组合体。

那么gzip和deflate是什么关系呢？

> gzip文件格式说明：

* > 10字节的头，包含[幻数](https://zh.wikipedia.org/wiki/%E9%AD%94%E8%A1%93%E6%95%B8%E5%AD%97_(%E7%A8%8B%E5%BC%8F%E8%A8%AD%E8%A8%88)) "魔术数字 (程序设计)")、版本号以及时间戳
  >
* > 可选的扩展头，如原文件名
  >
* > 文件体，包括DEFLATE压缩的数据
  >
* > 8字节的尾注，包括[CRC-32](https://zh.wikipedia.org/wiki/%E5%BE%AA%E7%8E%AF%E5%86%97%E4%BD%99%E6%A0%A1%E9%AA%8C "循环冗余校验")校验和以及未压缩的原始数据长度
  >

显然，gzip会用到deflate算法压缩数据，但是还会添加一些其他的信息到输出结果中。

打包 zlib 实现了读取和写入zlib格式压缩数据，如 RFC 1950中所述。

实现提供了在读取和压缩期间解压缩的过滤器。例如，要

将压缩数据写入缓冲区：

```javascript
var b bytes.Buffer
w := zlib.NewWriter(&b)
w.Write([]byte("hello, world\n"))
w.Close()
```

 并读回数据：

```javascript
r, err := zlib.NewReader(&b)
io.Copy(os.Stdout, r)
r.Close()
```

##### 3）写入文件

file.write(compressData)

### 如何实现cat-file命令？

#### 0、分析命令行参数

#### 1、读文件

1）去命令行中的object参数的[:2]读文件夹

2）遍历文件夹中的所有object文件

#### 2、解压：compress/zlib

将解压的内容放到byte[]数组中

#### 3、根据hash-object的结构来分解byte[]数组

-t

前4位为blob或者tree

-s

输出文件的大小

-p

输出文件的内容

## 3.tree object

### （1）update-index

--add

添加文件到暂存区

如果是第一次添加文件进缓冲区，我们需要创建一个 index 文件

如果 index 文件已经存在则直接把暂存区的内容读取出来，注意要有个**解压**的过程。

然后把新的文件信息添加到暂存区中，把暂存区的内容压缩后存入 index 文件。

### （2）ls-files

--stage

查看暂存区（index目录）的内容（不带参数的 ls-files 指令是列出当前目录包括子目录下的所有文件）


### （3）write-tree

把暂存区的内容转换成一个 tree object


![1656828678515](image/readme/1656828678515.png)

图1 tree的结构图

对于文件夹我们需要递归下降解析 tree object
