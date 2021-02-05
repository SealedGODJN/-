# Java复习笔记



## 1 switch语句

### 知识点：

1、switch语句最好包含default部分。如果没有default部分，当没有一个case能匹配传入的值（switch表达式中的值）时，switch语句无法处理该值（相当于这一块程序没用，没有输出）。

2、例外：如果switch处理的值是enum类型的，且包含该类型的所有可能值的显示案例，则switch语句可以不包含default部分。



总结：

## 2 



## 5 明确的进行数据类型转换，不要依赖隐式类型转换

### 知识点：【待补充】



​															java数据精度表

| 四类   | 八种    | 字节数 | 数据表示范围                         |
| ------ | ------- | ------ | ------------------------------------ |
| 整型   | byte    | 1      | -128~127                             |
|        | short   | 2      | -32768~32767（2^15^-1）              |
|        | int     | 4      | -2^31^~2^31^-1                       |
|        | long    | 8      | -2^63^==L==~2^63^-1==L==             |
| 浮点型 | float   | 4      | -3.403==E==38==F==~3.403==E==38==F== |
|        | double  | 8      | -1.798==E==308~1.798==E==308         |
| 字符型 | char    | 2      | 表示一个字符，如（'a','A','0','家'） |
| 布尔型 | boolean | 1      | 只有两个值true、false                |

补充：

> float共32位的分布为 ：1bit（符号位） + 8bits（指数位）+ 23bits（尾数位）
>
> double共64位的分布为：1bit（符号位）+ 11bits（指数位）+ 52bits（尾数位）
>
> 在数学中，特别是在计算机相关的数字（浮点数）问题的表述中，有一个基本表达法：
> value of floating-point = significand x base ^ exponent , with sign 
> 译为中文表达即为：（浮点）数值 =    尾数   ×   底数 ^ 指数，（附加正负号）

> float 的==指数位为8位==，于是float的指数范围为：-127~128   
>
> double的==指数位为11位==，于是double的指数范围为：-1023~1024
>
> 
>
> float的范围为 -2 ^128 ~ 2^128，即约为：-3.40E38 ~ +3.40E38；
>
> double的范围为-2^1024 ~ +2^1024，即约为：-1.79E308 ~ +1.79E308。

> float和double的==精度==是由尾数的位数决定的。
>
> 浮点数在内存中是按科学计数法来存储的，其整数部分始终是一个==隐含着的“1”==，由于它是不变的，故不能对精度造成影响。
>
> float：2^23 = 8388608，共七位，意味着最多能有7位有效数字，但绝对能保证的为6位，也即float的精度为6~7位有效数字；
> double：2^52 = 4503599627370496，一共16位，同理，double的精度为15~16位。

### 为什么要明确数据类型的精度呢？

个人想法：

1、在平时编码时，很少的题目会要求我们注意精度。而在生产环境下，会经常遇到非常大的数字。

2、解决方法：使用bigdecimal



### 注意：

1、当我们这样定义long a = 3；而不是long a = 3L，则默认表示为int型。这就是为什么long x = 9223372036854775807 （2^63-1）会报错，因为它已经超过int的大小范围了.

2、与1相反的是，当我们在定义float a = 3.0；而不是float a = 3.0F时，则默认表示为double型。别问我为什么Java这样定义数据，我也不懂它的任性- -|||



### 举例：

```java
long a = 1089212346l;
float b = 1.0f;
b = a;
System.out.println(b);
// 1.08921229E9

long a = 1089212234l;
float b = 1.0f;
b = a;
System.out.println(b);
// 1.08921229E9
// 结果相同，说明float只取前7位有效数字
```

将整型的数字转为浮点型，float取整型数字的==前7位有效数字==（当long型+float型运算时，会在得到的结果上取7位有效数字（包括小数点后的数值）存放在4个字节中，转成float型。这7位有效数字是==从高往低取==的，余下部分则自动被舍去了。）



```java
double hillHeight = 2147483642;
hillHeight += 1.0; 
System.out.println(hillHeight);
short roomWidth = 533;
int treeTall = 6783;
long cableLen = 4664382371590123456L; // 2的62次方多一点，cableLen*2之后，如果不先转换为double，会超出long的范围（2^63-1）
float singleRoomWidth = roomWidth / 5.0f;
double averageTall = treeTall / 30.; // 30.而不是30
double totalLen = (double) cableLen * 2;
System.out.println(singleRoomWidth);
System.out.println(averageTall);
System.out.println(totalLen);

// 2.147483643E9
// 106.6
// 226.1
// 9.328764743180247E18

short roomWidth = 533;
int treeTall = 6783;
long cableLen = 4664382371590123456L;
float singleRoomWidth = roomWidth / 7;
double averageTall = treeTall / 30;
double totalLen = cableLen * 2;
System.out.println(roomWidth);
System.out.println(singleRoomWidth);
System.out.println(averageTall);
System.out.println(totalLen); // 整型long的溢出


// 533
// 76.0
// 226.0
// -9.1179793305293046E18
```

