// demo01
var a = 20;
function fn() {
  console.log(this.a); // this: window
}
fn();

// demo02
var a = 20;
function fn() {
  function foo() {
    console.log(this.a); // this: fn【错误】应该为window
  }
  foo();
}
fn();

// demo03
var a = 20; // this: window
var obj = {
  a: 10,
  c: this.a + 20, // this: obj【错误】应该为window
  fn: function () {
    return this.a; // this: obj
  }
}

console.log(obj.c); // 30【错误】应该为40
console.log(obj.fn()); // 10