// demo06

var a = 10;
var obj = {
  a: 20
}

function fn() {
  console.log(this.a);
}

fn.call(obj); // 20