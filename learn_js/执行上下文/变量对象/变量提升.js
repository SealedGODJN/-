// 函数提升
sayHi() // Hi there!

function sayHi() {
    console.log('Hi there!')
}

// 因为函数声明在编译阶段会被添加到词法环境（Lexical Environment）中，当JavaScript引擎遇到sayHi()函数时，它会从词法环境中找到这个函数并执行它。
// lexicalEnvironment = {
//   sayHi: < func >
// }




// ======================================================================================================



// var变量提升
console.log(name)   // 'undefined'
var name = 'John Doe'
console.log(name)   // John Doe

// 上面的代码实际上分为两个部分：

// var name表示声明变量name
// = 'John Doe'表示的是为变量name赋值为'John Doe'。
// var name    // 声明变量
// name = 'John Doe' // 赋值操作


// 编译阶段
lexicalEnvironment = {
    name: undefined
}

// 执行阶段
lexicalEnvironment = {
name: 'John Doe'
}


// ======================================================================================================

// 函数表达式也不会被“提升”。helloWorld是一个默认值是undefined的变量，而不是一个function。

helloWorld();  // TypeError: helloWorld is not a function

var helloWorld = function(){
  console.log('Hello World!');
}

// ======================================================================================================

// let & const 提升
console.log(a)  // ReferenceError: a is not defined
let a = 3;

// 事实上所有的声明（function, var, let, const, class）都会被“提升”。
// 但是只有使用var关键字声明的变量才会被初始化undefined值，而let和const声明的变量则不会被初始化值。


// 时间盲区 === 暂时性死区
// 只有在执行阶段JavaScript引擎在遇到他们的词法绑定(赋值)时，他们才会被初始化。
// 这意味着在JavaScript引擎在声明变量之前，无法访问该变量。
let a;
console.log(a)  // undefined
a = 5;

// 编译阶段
// lexicalEnvironment = {
//     a: <uninitialized>
// }


// ======================================================================================================

// tips： 我们可以在let和const声明之前使用他们，只要代码不是在变量声明之前执行:
function foo() {
    console.log(name)
}

let name = 'John Doe'

foo()   // 'John Doe'


// ======================================================================================================

// Class提升
// 同let和const一样，class在JavaScript中也是会被“提升”的，在被真正赋值之前都不会被初始化值, 同样受Temporal Dead Zone的影响。
let peter = new Person('Peter', 25)

class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
}

console.log(peter) // ReferenceError: Person is not defined

let John = new Person('John', 25); 
console.log(John) // Person { name: 'John', age: 25 }

// 作者：哎嘿咬我呀
// 链接：https://juejin.cn/post/6844903895341219854
// 来源：稀土掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。