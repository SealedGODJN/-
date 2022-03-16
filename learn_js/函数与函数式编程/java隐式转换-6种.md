# [JavaScript的隐式转换 ](https://www.cnblogs.com/Songyc/p/4290202.html)

JavaScript的数据类型分为六种，分别为null,undefined,boolean,string,number,object。object是引用类型，其它的五种是基本类型或者是原始类型。我们可以用typeof方法打印来某个是属于哪个类型的。不同类型的变量比较要先转类型，叫做类型转换，类型转换也叫隐式转换。隐式转换通常发生在运算符加减乘除，等于，还有小于，大于等。。

```
typeof '11'  //string       
typeof(11) 　//number
'11' < 4     //false
```

## **基本类型的转换**

------

下面先讲加减乘除：

1.字符串加数字,数字就会转成字符串。

2.数字减字符串，字符串转成数字。如果字符串不是纯数字就会转成NaN。字符串减数字也一样。两个字符串相减也先转成数字。

3.乘，除，大于，小于跟减的转换也是一样。



```
//隐式转换 + - * == / 
// + 
10 + '20'    //'2010'
// -
10 - '20'    //-10
10 - 'one'   //NaN
10 - '100a'  //NaN
// *
10*'20'      //200
'10'*'20'    //200
// /
20/'10'      //2
'20'/'10'    //2
'20'/'one'　 //NaN
```



 再来看看一组 == 的。

1.undefined等于null

2.字符串和数字比较时，字符串转数字

3.数字为布尔比较时，布尔转数字

4.字符串和布尔比较时，两者转数字



```
// ==
undefined == null;    //true
'0' == 0;        　　  //true,字符串转数字
0 == false;           //true,布尔转数字
'0' == false;    　　　//true,两者转数字
null == false;     　 //false
undefined == false; 　//false
```



###  

## **引用类型的转换**

------

基本类型间的比较相对简单。引用类型和基本类型的比较就相对复杂一些，先要把引用类型转成基本类型，再按上述的方法比较。引用类型转布尔全是true。比如空数组，只要是对象就是引用类型，所以[]为true。引用类型转数字或者字符串就要用valueOf()或者toString();对象本身就继承了valuOf()和toString(),还可以自定义valueOf()和toString()。==根据不同的对象用继承的valueOf()转成字符串,数字或本身，而对象用toString就一定转为字符串。一般对象默认调用valueOf()。==

1.对象转数字时，调用valueOf();

2.对象转字符串时，调用toString();

先看看下面的例子：

```
0 == [];        // true, 0 == [].valueOf(); -> 0 == 0;
'0' == [];      // false, '0' == [].toString(); -> '0' == '';
2 == ['2'];     // true, 2 == ['2'].valueOf(); -> 2 == '2' -> 2 == 2;
'2' == [2];     // true, '2' == [2].toString(); -> '2' =='2';

[] == ![];      //true, [].valueOf() == !Boolean([]) -> 0 == false -> 0 == 0;
```

对象转成数字时，调用valueOf()，在这之前先调用的是toString();所以我猜valueOf方法是这样的。So上面的例子 0 == []要改成下面更合理。无论如何，[]最后是转成0的。

```
var valueOf = function (){
    var str = this.toString();    //先调用toString(),转成字符串
    //...
}
0 == [];        // true, 0 == [].valueOf(); -> 0 == '0' -> 0 == 0;
```

自定义的valueOf()和toString();

1.自定义的valueOf()和toString()都存在，会默认调用valueOf();

2.如果只有toString(),则调用toString();

```
var a = [1];

a.valueOf = function (){ return 1;}
a.toString = function (){ return '1';}

a + 1;         // 2, valueOf()先调用
```

去掉valueOf()就会调用toString()。



```
var a = [1];

a.valueOf = function (){ return 1;}
a.toString = function (){ return '1';}

a + 1;         // 2, 先调用valueOf()
//去掉valueOf
delete a.valueOf;
a + 1;        // '11', 调用toString()
```



如果返回其它会怎么样呢？

```
var a = [1];

a.valueOf = function (){return ;}
a.toString = function (){return 1 ;};

1 - a;        //NaN
```

其它对象 调用valueOf()转成不同的类型：



```
var a = {};
a.valueOf();    //Object {}
var a = [];
a.valueOf();    //[]    自己本身
var a = new Date();
a.valueOf();    //1423812036234  数字
var a = new RegExp();
a.valueOf();    //    /(?:)/  正则对象
```



 

引用类型之间的比较是内存地址的比较，不需要进行隐式转换，这里不多说。

```
[] == []  //false 地址不一样

var a = [];
b = a;
b == a   //true
```

 

## **显式转换** 

------

显式转换比较简单，可以直接用类当作方法直接转换。

```
Number([]);        //0
String([]);        //''
Boolean([]);       //true
```

还有更简单的转换方法。

```
3 + ''    // 字符串'3'
+'3'      // 数字3
!!'3'     // true
```

 

参考资料：

<<JavaScript权威指南 第六版>>第三章