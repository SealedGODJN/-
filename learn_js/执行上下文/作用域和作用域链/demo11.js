// demo11
function foo() {
    var a = 20;
    var b = 30;
  
    function bar() {
      return a + b; // 访问了foo中的变量a b，产生了闭包
    }
  
    return bar;
  }
  
  var bar = foo();
  bar();