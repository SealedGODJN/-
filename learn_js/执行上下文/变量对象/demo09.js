// demo09
function test() {
    console.log(foo);
    console.log(bar);

    var foo = 'Hello'; // 在demo2中，在执行之前只有foo函数变量和var bar变量，然后执行到第四行时才给foo重新赋值为‘hello’,执行到第六行值给bar 赋值为函数。
    console.log(foo);
    var bar = function () {
        return 'world';
    }

    function foo() {
        return 'hello';
    }
}

test();

// 创建过程完成后，执行过程从上往下依次执行