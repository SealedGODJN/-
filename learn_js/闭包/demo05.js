// demo05
(function () {

    var a = 10;
    var b = 20;
  
    var test = {
      m: 20,
      add: function (x) {
        return a + x;
      },
      sum: function () {
        return a + b + this.m;
      },
      mark: function (k, j) {
        return k + j;
      }
    }
  
    window.test = test;
  
  })();
  
  test.add(100);
  test.sum();
  test.mark();
  
  var _mark = test.mark;
  _mark();