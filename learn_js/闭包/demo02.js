// demo02
var fn;
var m = 20;
function foo() {
  var a = 2;
  function baz(a) {
    console.log(a);
  }
  fn = baz;
}
function bar() {
  fn(m);
}

foo();
bar(); // 20

// 闭包的形成需要两个条件。

// 1、在函数内部创建新的函数；
// 2、新的函数在执行时，访问了函数的变量对象；