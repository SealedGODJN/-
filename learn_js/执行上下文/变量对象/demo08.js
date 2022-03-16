// demo08
function test() {
    console.log(a); // a: undefined
    // alert(a);
    console.log(foo()); // foo: <function reference>
    // alert(foo());

    var a = 1; // 在demo1中，函数执行之前只有foo函数变量和a undefined变量，然后开始执行，当执行到第四行时，才开始给a赋值。
    function foo() {
        return 2;
    }
}

// test();

function display(){
	document.getElementById("demo").innerHTML=test();
}







// 编译阶段
// // 创建过程
// testEC = {
//     // 变量对象
//     VO: {},
//     scopeChain: {}
// }

// // 因为本文暂时不详细解释作用域链，所以把变量对象专门提出来说明

// // VO 为 Variable Object的缩写，即变量对象
// VO = {
//     arguments: {...},  //注：在浏览器的展示中，函数的参数可能并不是放在arguments对象中，这里为了方便理解，我做了这样的处理
//     foo: <foo reference>  // 表示foo的地址引用
//     a: undefined
// }

// // 执行阶段
// VO ->  AO   // Active Object 活动对象！！！
// AO = {
//     arguments: {...},
//     foo: <foo reference>,
//     a: 1,
//     this: Window
// }

// //执行顺序发生变化：
// function test() {
//     function foo() {
//         return 2;
//     }
//     var a;
//     console.log(a);
//     console.log(foo());
//     a = 1;
// }

// test();