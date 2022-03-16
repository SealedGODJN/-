// demo01

var fn;
function foo() {
  var a = 2;
  function baz() {
    console.log(a);
  }
  fn = baz;
}
function bar() {
  fn();
}

foo();
bar(); // 2