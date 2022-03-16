setTimeout(function () { // 4.a = 10
    console.log(a);
  }, 0);
  
  var a = 10;
  
  console.log(b); // 1.undefined
  console.log(fn); // 2.function
  
  var b = 20;
  
  function fn() {
    setTimeout(function () {
      console.log('setTImeout 10ms.');
    }, 10);
  }
  
  fn.toString = function () {
    return 30;
  }
  
  console.log(fn); // 3.fn.toString = 30 ???
  
  setTimeout(function () { // 6.setTimeout 20ms
    console.log('setTimeout 20ms.');
  }, 20);
  
  fn(); // 5.setTimeout 10ms