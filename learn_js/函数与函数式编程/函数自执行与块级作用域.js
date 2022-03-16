
// 在ES5中，没有块级作用域，因此我们常常使用函数自执行的方式来模仿块级作用域，这样就提供了一个独立的执行上下文，结合闭包，就为模块化提供了基础。
// 而函数自执行，其实是匿名函数的一种应用。
// 一个模块往往可以包括：私有变量、私有方法、公有变量、公有方法。

// (function() {
//     // ...
//  })();

// 根据作用域链的单向访问，外面可能很容易知道在这个独立的模块中，外部执行环境是无法访问内部的任何变量与方法的，
// 因此我们可以很容易的创建属于这个模块的私有变量与私有方法。

(function() {
    // 私有变量
    var age = 20;
    var name = 'Tom';

    // 私有方法
    function getName() {
        return `your name is ` + name;
    }

    function getAge() {
        return `your age is ` + age;
    }

    // 将引用保存在外部执行环境的变量中，形成闭包
    Window.getAge = getAge;

})();


// // 使用函数自执行的方式创建模块
// (function(window, undefined) {

//     // 声明jQuery构造函数
//      var jQuery = function(name) {

//         // 主动在构造函数中，返回一个jQuery实例
//          return new jQuery.fn.init(name);
//      }

//     // 添加原型方法
//      jQuery.prototype = jQuery.fn = {
//          constructor: jQuery,
//          init:function() { ... },
//          css: function() { ... }
//      }
//      jQuery.fn.init.prototype = jQuery.fn;

//     // 将jQuery改名为$，并将引用保存在window上，形成闭包，对外开放jQuery构造函数，这样我们就可以访问所有挂载在jQuery原型上的方法了
//      window.jQuery = window.$ = jQuery;
//  })(window);

// // 在使用时，直接执行了构造函数，因为在jQuery的构造函数中通过一些手段，返回的是jQuery的实例，所以我们就不用再每次用的时候自己new一个实例
// $('#div1');


// 自执行创建模块
(function() {
    // states 结构预览
    // states = {
    //     a: 1,
    //     b: 2,
    //     m: 30,  
    //     o: {}
    // }
    var states = {};  // 私有变量，用来存储状态与数据

    // 判断数据类型
    function type(elem) {
        if(elem == null) {
            return elem + '';
        }
        return toString.call(elem).replace(/[\[\]]/g, '').split(' ')[1].toLowerCase();
    }


    /**
     * @Param name 属性名
     * @Description 通过属性名获取保存在states中的值
    */
    function get(name) {
        return states[name] ? states[name] : '';
    }

    function getStates() {
        return states;
    }

    /*
    * @param options {object} 键值对
    * @param target {object} 属性值为对象的属性，只在函数实现时递归中传入
    * @desc 通过传入键值对的方式修改state树，使用方式与小程序的data或者react中的setStates类似
    */
    function set(options, target) {
        var keys = Object.keys(options);
        var o = target ? target : states;

        keys.map(function(item) {
            if(typeof o[item] == 'undefined') {
                o[item] = options[item];
            }
            else {
                type(o[item]) == 'object' ? set(options[item], o[item]) : o[item] = options[item];
            }
            return item;
        })
    }

    // 对外提供接口
    window.get = get;
    window.set = set;
    window.getStates = getStates;
})()

// 具体使用如下

set({ a: 20 });     // 保存 属性a
set({ b: 100 });    // 保存属性b
set({ c: 10 });     // 保存属性c

// 保存属性o, 它的值为一个对象
set({
    o: {
        m: 10,
        n: 20
    }
})

// 修改对象o 的m值
set({
    o: {
        m: 1000
    }
})

// 给对象o中增加一个c属性
set({
    o: {
        c: 100
    }
})
console.log(getStates())
