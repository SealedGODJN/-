// 在构造函数中添加方法
function Person(name, age) {
    this.name = name;
    this.age = age;
    // 在构造函数内部中添加方法
    this.getAge = function() {
        return this.age;
    }
    this.getName = function() {
        return this.name;
    }
}
// 给原型添加方法
Person.prototype.getName = function() {
    return this.name;
}

// 在对象中添加方法
var a = {
    m: 20,
    getM: function() {
        return this.m;
    }
}

var person = new Person("fuck", 20);

person.getName();