(function () {
    var a = 10;
    var b = 20;
  
    function add(num1, num2) {
      var num1 = !!num1 ? num1 : a;
      var num2 = !!num2 ? num2 : b;
  
      return num1 + num2;
    }
  
    window.add = add;
  })();
  
  add(10, 20);