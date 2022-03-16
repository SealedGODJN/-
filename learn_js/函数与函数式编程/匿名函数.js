var a = 10;
var fn = function(bar, num) {
    return bar() + num;
}

fn(function() {
    return a;
}, 20)

// 由于匿名函数传入另一个函数之后，最终会在另一个函数中执行，因此我们也常常称这个匿名函数为回调函数。