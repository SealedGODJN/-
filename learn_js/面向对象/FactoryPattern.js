// // learn_js/面向对象

// ======================================================================================

// 使用上面的方式创建对象很简单，但是在很多时候并不能满足我们的需求。
// 就以person对象为例。假如我们在实际开发中，不仅仅需要一个名字叫做TOM的person对象，
// 同时还需要另外一个名为Jake的person对象，
// 虽然他们有很多相似之处，但是我们不得不重复写两次。

var perTom = {
    name: 'TOM',
    age: 20,
    getName: function() {
        return this.name
    }
};

var perJake = {
    name: 'Jake',
    age: 22,
    getName: function() {
        return this.name
    }
}


// ======================================================================================


// 很显然这并不是合理的方式，当相似对象太多时，大家都会崩溃掉。
// 我们可以使用工厂模式的方式解决这个问题。
// 顾名思义，工厂模式就是我们提供一个模子，然后通过这个模子复制出我们需要的对象。我们需要多少个，就复制多少个。

var createPerson = function(name, age) {

    // 声明一个中间对象，该对象就是工厂模式的模子
    var o = new Object();

    // 依次添加我们需要的属性与方法
    o.name = name;
    o.age = age;
    o.getName = function() {
        return this.name;
    }

    return o;
}

// 创建两个实例
var perTom = createPerson('TOM', 20);
var PerJake = createPerson('Jake', 22);

// ======================================================================================

// 第一个麻烦就是这样处理，我们没有办法识别对象实例的类型。使用instanceof可以识别对象的类型，如下例子：

var obj = {};
var foo = function() {}

console.log(obj instanceof Object);  // true
console.log(foo instanceof Function); // true
// 因此在工厂模式的基础上，我们需要使用构造函数的方式来解决这个麻烦。

// ======================================================================================

// 三、构造函数
// 在JavaScript中，new关键字可以让一个函数变得与众不同。通过下面的例子，我们来一探new关键字的神奇之处。

function demo() {
    console.log(this);
}

demo();  // this指向window
new demo();  // this指向demo



// ======================================================================================

// 使用new之后，函数内部发生了一些变化，让this指向改变。那么new关键字到底做了什么事情呢。
// 其实我之前在文章里用文字大概表达了一下new到底干了什么

// 先一本正经的创建一个构造函数，其实该函数与普通函数并无区别
var Person = function(name, age) {
    this.name = name;
    this.age = age;
    this.getName = function() {
        return this.name;
    }
}

// 将构造函数以参数形式传入
function New(func) {

    // 声明一个中间对象，该对象为最终返回的实例
    var res = {};
    if (func.prototype !== null) {

        // 将实例的原型指向构造函数的原型
        res.__proto__ = func.prototype;
    }

    // ret为构造函数执行的结果，这里通过apply，将构造函数内部的this指向修改为指向res，即为实例对象
    var ret = func.apply(res, Array.prototype.slice.call(arguments, 1));

    // 当我们在构造函数中明确指定了返回对象时，那么new的执行结果就是该返回对象
    if ((typeof ret === "object" || typeof ret === "function") && ret !== null) {
        return ret;
    }

    // 如果没有明确指定返回对象，则默认返回res，这个res就是实例对象
    return res;
}

// 通过new声明创建实例，这里的p1，实际接收的正是new中返回的res
var p1 = New(Person, 'tom', 20);
console.log(p1.getName());

// 当然，这里也可以判断出实例的类型了
console.log(p1 instanceof Person); // true

// 为了能够判断实例与对象的关系，我们就使用构造函数来搞定。


// JavaScript内部再通过其他的一些特殊处理，将var p1 = New(Person, 'tom', 20);
//  等效于var p1 = new Person('tom', 20);。就是我们认识的new关键字了。


// ======================================================================================

