// learn_js/执行上下文/this

var a = 10;
var obj = {
  a: 20
}

function fn() {
  this = obj; // 这句话试图修改this，运行后会报错
  // // Uncaught SyntaxError: Invalid left-hand side in assignment
  console.log(this.a);
}

fn();