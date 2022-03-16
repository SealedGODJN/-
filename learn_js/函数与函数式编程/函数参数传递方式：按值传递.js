// learn_js/函数与函数式编程

/**
 * 都是值传递
 */

// var a = 20;

// function fn(a) {
//     a = a + 10;
//     return a;
// }
// fn(a);
// console.log(a); // 20

/**
 * 都是值传递
 */

// var a = { m: 10, n: 20 }
// function fn(a) {
//     a.m = 20;
//     return a;
// }

// fn(a);
// console.log(a);   // { m: 20, n: 20 }

var person = {
    name: 'Nicholas',
    age: 20
}

function setName(obj) {  // 传入一个引用
    obj = {};   // 将传入的引用指向另外的值
    obj.name = 'Greg';  // 修改引用的name属性
}

setName(person);
console.log(person.name);  // Nicholas 未被改变