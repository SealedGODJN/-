// 题目
for (var i = 1; i <= 5; i++) { // for循环先执行，则i=6，setTimeout的所有事件进入事件队列，对应的所有事件都是i=6
    setTimeout(function timer() {
      console.log(i);
    }, i * 1000);
  }


// 改进1
var time = 1;
for (var i = 1; i <= 5; i++) {
    setTimeout(function timer() {
      console.log(time);
      time += 1;
    }, 1000);
  }

// 标准答案
for (var i = 1; i <= 5; i++) {
(function (i) {
    setTimeout(function timer() {
    console.log(i);
    }, i * 1000);
})(i)
}

for (var i = 1; i <= 5; i++) {
  setTimeout((function (i) {
    return function() {
      console.log(i);
    }
  })(i), i * 1000);
}