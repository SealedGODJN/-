# [this绑定方式总结](https://www.cnblogs.com/luoyanan/p/11922483.html)

最近在回顾js的一些基础知识，把《你不知道的js》系列又看了一遍，this始终是重中之重，还是决定把this相关知识做一个系统的总结，也方便自己日后回顾。

## this的四条绑定规则

### 1.默认绑定

这是最常用的函数调用类型：独立函数调用（即函数是直接使用不带任何修饰的函数引用进行调用的）。可以把这条规则看作是无法应用其他规则时的默认规则。
默认绑定的this在非严格模式下指向window,严格模式下指向undefined,比如下面的函数foo在非严格模式下：

```javascript
var a = 2;
function foo(){
    var a = 3;
    console.log(this.a);
}
foo(); //2
```

这里的foo()方法内的this指向了window，因此window.a = 2;

严格模式下，this.指向undefined，因此访问this.a会报错：

```javascript
var a = 2;
function foo(){
	"use strict";
	var a = 3;
	console.log(this.a);
}
foo(); //Uncaught TypeError: Cannot read property 'a' of undefined
```

### 2.隐式绑定

如果调用位置上有上下文对象，或者说被某个对象“拥有”或者“包
含”，则使用隐式绑定。

```javascript
function foo() {
    console.log( this.a );
}
var obj = {
    a: 2,
    foo: foo
};
obj.foo(); // 2
```

上例中的foo是通过obj.foo()的方式调用的，调用位置会使用obj上下文来引用函数，因此foo中的this指向了obj。
另外foo是当做引用被加入到obj中的，但是无论是直接在obj 中定义还是先定义再添加为引用属性，foo严格上来说都不属于obj,因此上述定义里面的“拥有”与“包含”加上了引号，这样说是为了方便理解。
常见的隐式调用场景：
`obj.fn();`
`arguments[i]();`//其实就是将点的调用方式变为了[]调用
`el.onClick(function(){console.log(this);//this指向el})`

#### 隐式丢失

先来看一段代码：

```javascript
function foo() {
	console.log( this.a );
}
var obj = {
	a: 2,
	foo: foo
};
var bar = obj.foo; // 函数别名！
var a = "global"; // a 是全局对象的属性
bar(); // "global"
```

上述代码其实只用看调用的方式：bar()，这其实是一个不带任何修饰的函数调用，因此应用了默认绑定。
还有一种参数传递的方式也会发生隐式丢失，原理其实跟上述例子一样：

```javascript
function foo() {
	console.log( this.a );
}
function doFoo(fn) {
	// fn 其实引用的是foo
	fn(); // <-- 调用位置！
}
var obj = {
	a: 2,
	foo: foo
};
var a = "global"; // a 是全局对象的属性
doFoo( obj.foo ); // "global"
```

### 显示绑定

使用call,apply和bind方法可以指定绑定函数的this的值，这种绑定方法叫显示绑定。

```javascript
function foo() {
    console.log( this.a );
}
var obj = {
    a:2
};
foo.call( obj ); // 2
```

通过foo.call(obj)，我们可以在调用foo 时强制把它的this 绑定到obj 上

### new绑定

new操作符可以基于一个“构造函数”新创建一个对象实例，new的实例化过程如下：

1. 创建（或者说构造）一个全新的对象。
2. 这个新对象会被执行[[ 原型]] 连接。
3. 这个新对象会绑定到函数调用的this。
4. 如果函数没有返回其他对象，那么new 表达式中的函数调用会自动返回这个新对象。
   明确了new的实例化过程后，思考如下代码：

```javascript
function foo(a) {
    this.a = a;
}
var bar = new foo(2);
console.log( bar.a ); // 2
```

new foo(2)后新创建了个实例对象bar,然后把这个新对象bar绑定到了foo函数中的this，因此执行this.a = a后其实是把a赋给了bar.a

## 优先级

一般情况下this的绑定会根据上述四条绑定规则来，那么他们同时出现时，该以怎样的顺序来判断this的指向？下面是具体的规则：

1. 函数是否在new 中调用（new 绑定）？如果是的话this 绑定的是新创建的对象（ var bar = new foo() ）。
2. 函数是否通过call、apply（显式绑定）或者硬绑定调用？如果是的话，this 绑定的是指定的对象（ var bar = foo.call(obj2) ）。
3. 函数是否在某个上下文对象中调用（隐式绑定）？如果是的话，this 绑定的是那个上下文对象。（ var bar = obj1.foo() ）
4. 如果都不是的话，使用默认绑定。如果在严格模式下，就绑定到undefined，否则绑定到全局对象。（ var bar = foo() ）

## 绑定例外

### 1.使用call,appy,bind这种显式绑定的方法，参数传入null或者undefined作为上下文时，函数调用还是会使用默认绑定

```javascript
function foo() {
    console.log( this.a ); // this: window
}
var a = 2;
foo.call( null ); // 2
```

#### 什么情况下需要将上下文传为null呢？

**1.使用bind函数来实现柯里化**

```javascript
function foo(a,b) {
    console.log(a,b);
}
// 使用 bind(..) 进行柯里化
var bar = foo.bind( null, 2 );
bar( 3 ); // 2,3
```

**2.使用apply(..) 来展开一个数组，并当作参数传入一个函数**

```javascript
function foo(a,b) {
    console.log(a,b);
}
// 把数组展开成参数
foo.apply( null, [2, 3] ); // 2,3
```

其实上面两种使用场景其实都不关心call/app/bind第一个参数的值是什么，只是想传个占位值而已。

但是总是传入null可能会出现一些难以追踪的bug，比如说当你在使用的第三方库中的某个函数中有this时，this会被错误的绑定到全局对象上，造成一些难以预料的后果（修改全局变量）

```javascript
var a = 1;//全局变量
const Utils = {
	a: 2,
	changeA: function(a){
		this.a = a;
	}
}
Utils.changeA(3);
Utils.a //3
a //1
Utils.changeA.call(null,4); // this指向了window，则this.a代表的是全局变量中的a
Utils.a //3
a //4,修改了全局变量a！
```

更安全的做法：

```javascript
var o = Object.create(null);
Utils.changeA.call(o,6);
a //1, 全局变量没有修改
o.a // 6 改的是变量o
```

### 2.间接引用

```javascript
function foo() {
	console.log( this.a );
}
var a = 2;
var o = { a: 3, foo: foo };
var p = { a: 4 };
o.foo(); // 3
(p.foo = o.foo)(); // 2
```

赋值表达式p.foo = o.foo 的返回值是目标函数的引用，因此调用位置是foo() 而不是p.foo() 或者o.foo()。根据我们之前说过的，这里会应用默认绑定。

## this词法（箭头函数）

上述的几种规则适用于所有的正常函数，但不包括ES6的箭头函数。箭头函数不使用this的四种标准规则，而是根据外层（函数或者全局）作用域（词法作用域）来决定this

```javascript
function foo() {
// 返回一个箭头函数
	return (a) => {
		//this 继承自foo()
		console.log( this.a );
	};
}
var obj1 = {
	a:2
};
var obj2 = {
	a:3
};
var bar = foo.call( obj1 );
bar.call( obj2 ); // 2, 不是3 ！
```

foo() 内部创建的箭头函数会捕获调用时foo() 的this。由于foo() 的this 绑定到obj1，bar（引用箭头函数）的this 也会绑定到obj1，箭头函数的绑定无法被修改。（new 也不行！）

## 几个例子加深理解

this的理论知识讲解得差不多了，来几个例子看看自己有没有理解全面：
1.经典面试题：以下输出结果是什么

```javascript
var length = 10;
function fn() {
    console.log(this.length);
}
var obj = {
  length: 5,
  method: function(fn) {
    fn(); // this: window
    arguments[0](); // this: Arguments(2)
  }
};
obj.method(fn, 1);
```

obj中method方法里面调用了两次fn。第一次是直接调用的“裸露”的fn,因此fn()中this使用默认绑定，this.length为10.第二次调用时通过arguments[0](https://www.cnblogs.com/luoyanan/p/11922483.html)的方式调用的，arguments[0]其实指向的就是fn，但是是通过obj[fn]这种对象上下文的隐式绑定的，因此this指向arguments，而arguments只有一个一项(method中只有fn一个参数)，因此arguments.length为2（之前作者写错了，不应该为1）。因此打印的结果为：

```undefined
10
2
```

2.以下输出什么

```javascript
var obj = {
    birth: 1990,
    getAge: function () {
        var b = this.birth; // 1990
        var fn = function () {
            return new Date().getFullYear() - this.birth; // this指向window或undefined
        };
        return fn();
    }
};
obj.getAge();
```

答案是严格模式下会 `报错`，非严格模式下输出 `NaN`
原因也是因为在调用obj.getAge()后，getAge方法内的this使用隐式绑定。但是return fn()的时候用的是“裸露的fn”使用默认绑定，fn里面的this指向window或者undefined。
使用箭头函数来修正this的指向：

```php
var obj = {
    birth: 1990,
    getAge: function () {
        var b = this.birth; // 1990
        var fn = () => new Date().getFullYear() - this.birth; // this指向obj对象
        return fn();
    }
};
obj.getAge(); // 25
```

使用箭头函数后，fn中的this在他的词法分析阶段就已经确定好了（即fn定义的时候），跟调用位置无关。fn的this指向外层的作用域（即getAge中的this）
3.以下输出为什么是'luo'

```javascript
var A = function( name ){ 
    this.name = name; // this仍然指向B
};
var B = function(){ 
    A.apply(this,arguments); // apply的优先级比new 低，则this指向不变
};
B.prototype.getName = function(){ 
    return this.name;
};
var b=new B('seven');  // B {name: "seven"} // new 优先级最高，则this: B
console.log( b.getName() ); // 输出:  'seven'
```

执行new B('seven')后会返回一个新对象b,并且B函数中的this会绑定到新对象b上，B的函数体内执行A.apply(this.arguments)也就是执行b.name = name;这个时候b的值就是{name:'luo'},所以b.getName()就能输出'seven'啦~

**实际在业务使用中，逻辑会更复杂一些，但是万变不离其宗，都按照上面写的规则来代入就好了**
