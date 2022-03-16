// 通过this绑定到全局对象
this.a2 = 20;

// 通过声明绑定到变量对象，但在全局环境中，变量对象就是它自身
var a1 = 10;

// 仅仅只有赋值操作，标识符会隐式绑定到全局对象
a3 = 30; // Uncaught SyntaxError: Invalid left-hand side in assignment

// 输出结果会全部符合预期
console.log(a1);
console.log(a2);
console.log(a3);