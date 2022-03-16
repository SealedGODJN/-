console.log(foo); // function foo
function foo() { console.log('function foo') }
var foo = 20;

// // 上栗的执行顺序为

// // 首先将所有函数声明放入变量对象中
// function foo() { console.log('function foo') }

// // function声明会比var声明优先级更高一点
// // 其次将所有变量声明放入变量对象中，但是因为foo已经存在同名函数，此时以函数值为准，而不会被undefined覆盖
// // var foo = undefined;

// // 然后开始执行阶段代码的执行
// console.log(foo); // function foo
// foo = 20;