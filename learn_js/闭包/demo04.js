// demo04

function foo() {
    var a = 2;
  
    return function bar() {
      var b = 9;
  
      return function fn() {
        console.log(a, b); // (foo) (bar)
      }
    }
  }
  
  var bar = foo();
  var fn = bar();
  fn();