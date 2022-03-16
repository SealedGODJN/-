# 函数柯里化

## 柯里化的定义

> 红宝书（第3版）：用于创建已经设置好了一个或多个参数的函数。基本方法是使用一个闭包返回一个函数。（P604）
>
> 维基百科：柯里化（英语：Currying），是把接受多个参数的函数变换成接受一个单一参数（最初函数的第一个参数）的函数，并且返回接受余下的参数而且返回结果的新函数的技术。（[原链接](https://zh.wikipedia.org/wiki/%E6%9F%AF%E9%87%8C%E5%8C%96)）

官方解释看得有点懵，大白话概括一下：

柯里化技术， **主要体现在函数里面返回函数** 。就是将多变量函数拆解为单变量（或部分变量）的多个函数并依次调用。

再直白一点：利用闭包，可以形成一个不销毁的私有作用域，把预先处理的内容都存在这个不销毁的作用域里面，并且返回一个函数，以后要执行的就是这个函数。

PS：如果还是不理解也没关系，跟闭包一样不用死扣定义，继续往下面看应用就行了。

## 柯里化的应用

柯里化有 3 个常见应用：

* 参数复用 – 当在多次调用同一个函数，并且传递的参数绝大多数是相同的，那么该函数可能是一个很好的柯里化候选
* 提前返回 – 多次调用多次内部判断，可以直接把第一次判断的结果返回外部接收
* 延迟计算/运行 – 避免重复的去执行程序，等真正需要结果的时候再执行

## 应用一：参数复用

如下名为 `uri` 的函数，接收 3 个参数，函数的作用是返回三个参数拼接的字符串。

```js
function uri(protocol, hostname, pathname) {
  return `${protocol}${hostname}${pathname}`;
}

// 测试一下
const uri1 = url('https://', 'www.fedbook.cn', '/function-curring/')
console.log(uri1)
```

上面这种写法的弊端是：当我们有很多网址时，会导致非常多重复的参数（比如 `https://` 就是重复的参数，我们在浏览器里面输入网址也不需要输入 http 或者 https）。

利用柯里化实现参数复用的思路：

* 原函数（称为函数 A）只设置一个参数（接收协议这个参数）；
* 在函数内部再创建一个函数（称为函数 B），函数 B 把刚才剩余的两个参数给补上，并返回字符串形式的 url；
* 函数 A 返回函数 B。

```js
function uri_curring(protocol) {
  return function(hostname, pathname) {
    return `${protocol}${hostname}${pathname}`; 
  }
}

// 测试一下
const uri_https = uri_curring('https://');

const uri1 = uri_https('www.fedbook.cn', '/frontend-languages/javascript/function-currying/');
const uri2 = uri_https('www.fedbook.cn', '/handwritten/javascript/10-实现bind方法/');
const uri3 = uri_https('www.wenyuanblog.com', '/');

console.log(uri1);
console.log(uri2);
console.log(uri3);
```

## 应用二：兼容性检测

::: warning

以下代码为了编写方便，会使用 ES6 的语法。实际生产环境中如果要做兼容性检测功能，需要转换成 ES5 语法。

:::

因为浏览器的发展和各种原因，有些函数和方法是不被部分浏览器支持的，此时需要提前进行判断，从而确定用户的浏览器是否支持相应的方法。

以事件监听为例，IE（IE9 之前） 支持的是 [`attachEvent`](https://developer.mozilla.org/zh-CN/docs/Web/API/EventTarget/addEventListener) 方法，其它主流浏览器支持的是 [`addEventListener`](https://developer.mozilla.org/zh-CN/docs/Web/API/EventTarget/addEventListener) 方法，我们需要创建一个新的函数来进行两者的判断。

```js
const addEvent  = function(element, type, listener, useCapture) {
  if(window.addEventListener) {
    console.log('判断为其它浏览器')
    // 和原生 addEventListener 一样的函数
    // element: 需要添加事件监听的元素
    // type: 为元素添加什么类型的事件
    // listener: 执行的回调函数
    // useCapture: 要进行事件冒泡或者事件捕获的选择
    element.addEventListener(type, function(e) {
      // 为了规避 this 指向问题，用 call 进行 this 的绑定
      listener.call(element, e);
    }, useCapture);
  } else if(window.attachEvent) {
    console.log('判断为 IE9 以下浏览器')
    // 原生的 attachEvent 函数
    // 不需要第四个参数，因为 IE 支持的是事件冒泡
    // 多拼接一个 on，这样就可以使用统一书写形式的事件类型了
    element.attachEvent('on' + type, function(e) {
      listener.call(element, e);
    });
  }
}

// 测试一下
let div = document.querySelector('div');
let p = document.querySelector('p');
let span = document.querySelector('span');

addEvent(div, 'click', (e) => {console.log('点击了 div');}, true);
addEvent(p, 'click', (e) => {console.log('点击了 p');}, true);
addEvent(span, 'click', (e) => {console.log('点击了 span');}, true);
```

上面这种封装的弊端是：每次写监听事件的时候调用 `addEvent` 函数，都会进行 `if...else...` 的兼容性判断。事实上在代码中只需要执行一次兼容性判断就可以了，把根据一次判定之后的结果动态生成新的函数，以后就不必重新计算。

那么怎么用函数柯里化优化这个封装函数？

```js
// 使用立即执行函数，当我们把这个函数放在文件的头部，就可以先进行执行判断
const addEvent  = (function() {
  if(window.addEventListener) {
    console.log('判断为其它浏览器')
    return function(element, type, listener, useCapture) {
      element.addEventListener(type, function(e) {
        listener.call(element, e);
      }, useCapture);
    }
  } else if(window.attachEvent) {
    console.log('判断为 IE9 以下浏览器')
    return function(element, type, handler) {
      element.attachEvent('on'+type, function(e) {
        handler.call(element, e);
      });
    }
  }
}) ();

// 测试一下
let div = document.querySelector('div');
let p = document.querySelector('p');
let span = document.querySelector('span');

addEvent(div, 'click', (e) => {console.log('点击了 div');}, true);
addEvent(p, 'click', (e) => {console.log('点击了 p');}, true);
addEvent(span, 'click', (e) => {console.log('点击了 span');}, true);
```

上述封装因为立即执行函数的原因，触发多次事件也依旧只会触发一次 if 条件判断。

这里使用了函数柯里化的两个特点：提前返回和延迟执行。

## 应用三：实现一个 add 函数

这是一道经典面试题，要求我们实现一个 add 函数，可以实现以下计算结果：

```js
add(1)(2)(3) = 6;
add(1, 2, 3)(4) = 10;
add(1)(2)(3)(4)(5) = 15;
```

通过这道题正好可以解释柯里化的延迟执行，直接上代码：

```js
function add() { // 闭包add
  // 将传入的不定参数转为数组对象
  let args = Array.prototype.slice.call(arguments); // args为闭包内部的函数inner访问外部函数add()中的变量

  // 递归：内部函数里面进行自己调用自己
  // 当 add 函数不断调用时，把第 N+1 个括号的参数加入到第 N 个括号的参数里面
  let inner = function() {
    args.push(...arguments);
    return inner;
  }
  
  inner.toString = function() {
    // args 里的值不断累加
    return args.reduce(function(prev, cur) {
      return prev + cur;  
    });
  };

  return inner;
}

// 测试一下
let result = add(1)(2)(3)(4);
console.log(result);
```

解释几个关键点：

**1）不定参数 `arguments` 需要转为数组对象：**

因为 `arguments` 并不是真正的数组，而是与数组类似对象，`Array.prototype.slice.call(arguments)` 能将具有 `length` 属性的对象转成数组。

**2）`toString` 隐形转换的特性：**

对于 `add(1)(2)(3)(4)`，执行每个括号的时候都返回 `inner` 函数，不断自己调用自己，每次内部函数返回的都是内部函数。

如果打印函数执行的最终返回结果，可以发现返回了一个字符串（原本的函数被转换为字符串返回了），这即是发生了隐式转换，而发生隐式转换是因为调用了内部的 `toString` 方法。

知道了这一点，我们就可以利用这个特性自定义返回的内容：重写 `inner` 函数的 `toString` 方法，在里面实现参数相加的执行代码。

值得一提的是，这种处理后能够返回正确的累加结果，但返回的结果是个函数类型（`function`），这是因为我们在用递归返回函数，内部函数在被隐式转换为字符串之前本来就是一个函数。


# 补充：

## 举例

如果我们想要验证一串数字是否是正确的手机号，按照普通的思路来做，大家可能是这样封装，如下：

```javascript
function checkPhone(phoneNumber) {
    return /^1[34578]\d{9}$/.test(phoneNumber);
}
```

而如果想要验证是否是邮箱呢？这么封装：

```javascript
function checkEmail(email) {
    return /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(email);
}
```

我们还可能会遇到验证身份证号，验证密码等各种验证信息，因此在实践中，为了统一逻辑，我们就会封装一个更为通用的函数，将用于验证的正则与将要被验证的字符串作为参数传入。

```javascript
function check(targetString, reg) {
    return reg.test(targetString);
}
```

但是这样封装之后，在使用时又会稍微麻烦一点，因为会总是输入一串正则，这样就导致了使用时的效率低下。

```javascript
check(/^1[34578]\d{9}$/, '14900000088');
check(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/, 'test@163.com');
```

这个时候，我们就可以借助柯里化，在check的基础上再做一层封装，以简化使用。

```javascript
var _check = createCurry(check);

var checkPhone = _check(/^1[34578]\d{9}$/);
var checkEmail = _check(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/);
```

最后在使用的时候就会变得更加直观与简洁了。

```javascript
checkPhone('183888888');
checkEmail('xxxxx@test.com');
```

经过这个过程我们发现，柯里化能够应对更加复杂的逻辑封装。当情况变得多变，柯里化依然能够应付自如。

虽然柯里化确实在一定程度上将问题复杂化了，也让代码更加不容易理解，但是柯里化在面对复杂情况下的灵活性却让我们不得不爱。

当然这个案例本身情况还算简单，所以还不能够特别明显的凸显柯里化的优势，我们的主要目的在于借助这个案例帮助大家了解柯里化在实践中的实践。


实践中我们常常会发现，在我们的某个项目中，针对于某一个数组的操作其实是固定的，也就是说，同样的操作，可能会在项目的不同地方调用很多次。

于是，这个时候，我们就可以在map函数的基础上，进行二次封装，以简化我们在项目中的使用。假如这个在我们项目中会调用多次的操作是将数组的每一项都转化为百分比 1 --> 100%。

普通思维下我们可以这样来封装。

```javascript
function getNewArray(array) {
    return array.map(function(item) {
        return item * 100 + '%'
    })
}

getNewArray([1, 2, 3, 0.12]);   // ['100%', '200%', '300%', '12%'];
```

而如果借助柯里化来二次封装这样的逻辑，则会如下实现：

```javascript
function _map(func, array) {
    return array.map(func);
}

var _getNewArray = createCurry(_map);

var getNewArray = _getNewArray(function(item) {
    return item * 100 + '%'
})

getNewArray([1, 2, 3, 0.12]);   // ['100%', '200%', '300%', '12%'];
getNewArray([0.01, 1]); // ['1%', '100%']
```

如果我们的项目中的固定操作是希望对数组进行一个过滤，找出数组中的所有Number类型的数据。借助柯里化思维我们可以这样做。

我采用了与check例子不一样的思维方向来想大家展示我们在使用柯里化时的想法。目的是想告诉大家，柯里化能够帮助我们应对更多更复杂的场景。

当然不得不承认，这些例子都太简单了，简单到如果使用柯里化的思维来处理他们显得有一点多此一举，而且变得难以理解。因此我想读者朋友们也很难从这些例子中感受到柯里化的魅力。不过没关系，如果我们能够通过这些例子掌握到柯里化的思维，那就是最好的结果了。在未来你的实践中，如果你发现用普通的思维封装一些逻辑慢慢变得困难，不妨想一想在这里学到的柯里化思维，应用起来，柯里化足够强大的自由度一定能给你一个惊喜。


## 函数的隐式转换

第二个要补充的知识点是函数的隐式转换。当我们直接将函数参与其他的计算时，函数会默认调用toString方法，直接将函数体转换为字符串参与计算。

```javascript
function fn() {
	return 20;
}
var result = fn + 10; // 30

```

我们可以重写函数的toString方法，让函数参与计算时，输出我们想要的结果。

```javascript
function fn() {
	return 10;
}
fn.toString = function() {return 30};
console.log(fn + 10); // 40

```


除此之外，当我们重写函数的valueOf方法也能够改变函数的隐式转换结果。

当我们同时重写函数的toString方法与valueOf方法时，最终的结果会取valueOf方法的返回结果。
