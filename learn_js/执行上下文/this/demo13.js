var a = 10;
var obj = {
  a: 20
}

function fn() {
  console.log(this.a);
}

fn(); // 10 // this : window
fn.call(obj); // 20 // this : Object