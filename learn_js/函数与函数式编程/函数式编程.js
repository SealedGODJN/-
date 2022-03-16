
// ======================================================================================

// 1、函数是第一等公民

// 所谓"第一等公民"（first class），指的是函数与其他数据类型一样，
// 处于平等地位，可以赋值给其他变量，也可以作为参数，传入另一个函数，
// 或者作为别的函数的返回值。这些场景，我们应该见过很多。

function getUser(path, callback) {
    return $.get(path, function(info) {
        return callback(info);
    })
}

getUser('/api/user',
    // resp为成功请求之后返回的数据
    console.log(resp))

// ======================================================================================

// 2、只用"表达式"，不用"语句"

// "表达式"（expression）是一个单纯的运算过程，总是有返回值；"语句"（statement）是执行某种操作，没有返回值。
// 函数式编程要求，只使用表达式，不使用语句。也就是说，每一步都是单纯的运算，而且都有返回值。

// // 需要改变元素的背景色
// var ele = document.querySelector('.test');
// function setBackgroundColor(color) {
//     ele.style.backgroundColor = color;
// }

// // 多处使用
// setBackgroundColor('red');
// setBackgroundColor('#ccc');

var ele = document.querySelector('.test');
function setBackgroundColor(color) {
    ele.style.backgroundColor = color;
}

// 多处使用
setBackgroundColor('red');
setBackgroundColor('#ccc');

// ======================================================================================

// 纯函数

// 相同的输入总会得到相同的输出，并且不会产生副作用的函数，就是纯函数。
// 所谓"副作用"（side effect），指的是函数内部与外部互动（最典型的情况，就是修改全局变量的值），产生运算以外的其他结果。
// 函数式编程强调没有"副作用"，意味着函数要保持独立，所有功能就是返回一个新的值，没有其他行为，尤其是不得修改外部变量的值。

function getLast(arr) {
    return arr[arr.length - 1];
}

function getLast_(arr) {
    return arr.pop();
}

var source = [1, 2, 3, 4];

var last = getLast(source); // 返回结果4 原数组不变
var last_ = getLast_(source); // 返回结果4 原数据最后一项被删除

// ======================================================================================

var source = [1, 2, 3, 4, 5];

source.slice(1, 3); // 纯函数 返回[2, 3] source不变
source.splice(1, 3); // 不纯的 返回[2, 3, 4] source被改变

source.pop(); // 不纯的
source.push(6); // 不纯的
source.shift(); // 不纯的
source.unshift(1); // 不纯的
source.reverse(); // 不纯的

// 我也不能短时间知道现在source被改变成了什么样子，干脆重新约定一下
source = [1, 2, 3, 4, 5];

source.concat([6, 7]); // 纯函数 返回[1, 2, 3, 4, 5, 6, 7] source不变
source.join('-'); // 纯函数 返回1-2-3-4-5 source不变
