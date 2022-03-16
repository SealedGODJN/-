// 全局作用域和函数作用域

var color = 'blue';

function changeColor() {
    var anthorColor = 'red';

    function swapColor() {
        var tempColor = anthorColor;
        anthorColor = color;
        color = tempColor;
    }
    swapColor();
    console.log(anthorColor);
}
changeColor();
console.log(color);

// console.log(anthorColor);
// 不能超出其作用域


// ============总结=================
// 单线程
// 同步执行，只有栈顶的上下文处于执行中，其他上下文需要等待
// 全局上下文只有唯一的一个，它在浏览器关闭时出栈
// 函数的执行上下文的个数没有限制
// 每次某个函数被调用，就会有个新的执行上下文为其创建，即使是调用的自身函数，也是如此。

// 作者：这波能反杀
// 链接：https://www.jianshu.com/p/a6d37c77e8db
// 来源：简书
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。