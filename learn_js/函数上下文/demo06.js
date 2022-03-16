var name = "window";

var p = {
  name: 'Perter',
  getName: function () {

    // 利用变量保存的方式保证其访问的是p对象
    var self = this;
    return function () {
      return self.name;
    }
  }
}

var getName = p.getName();
var _name = getName();
console.log(_name);

// var name = "window";

// var p = {
// name: 'Perter',
// getName: function() {
// return this.name
// // 利用变量保存的方式保证其访问的是p对象
// // var self = this;
// // return function() {
// // return self.name;
// // }
// }
// }

// var getName = p.getName();
// console.log(getName)//Perter
// var _name = getName;
// console.log(_name);//Perter

// 新手一枚，想知道为什么不直接return this.name 而要var self = this 呢？结果我试了都是一样。

// 闭包中的this指代的是全局意义上的name，那你这里的你就指代成window