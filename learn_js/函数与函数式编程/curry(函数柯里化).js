// learn_js/函数与函数式编程/curry(函数柯里化).js

// // 简单实现，参数只能从右到左传递
// function createCurry(func, args) {

//     var arity = func.length;
//     var args = args || [];

//     return function() {
//         var _args = [].slice.call(arguments);
//         [].push.apply(_args, args);

//         // 如果参数个数小于最初的func.length，则递归调用，继续收集参数
//         if (_args.length < arity) {
//             return createCurry.call(this, func, _args);
//         }

//         // 参数收集完毕，则执行func
//         return func.apply(this, _args);
//     }
// }

// 简单实现，参数只能从右到左传递
function createCurry(func, ...args) {

    var arity = func.length;
    var args = args || [];

    return function () {
        var _args = [].slice.call(arguments);
        [].push.apply(_args, args);
        console.log(_args)
        
        // 如果参数个数小于最初的func.length，则递归调用，继续收集参数
        if (_args.length < arity) {
            return createCurry.call(this, func, ..._args);
        }a
        
        // 参数收集完毕，则执行func
        return func.apply(this, _args);
    }
}
    
    const curryAdd = createCurry(add);
    let sum = curryAdd(2)(3)(4)(5); // 14
    console.log(sum)