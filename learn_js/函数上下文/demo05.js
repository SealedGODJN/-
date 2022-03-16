// 闭包的例子
function f1() {
    var n = 999;
    function f2() {
        alert(n);
    }
    // return f2();
    return f2;
}

var result = f1();

// result(); // 999

function display(){
	document.getElementById("demo").innerHTML=result();
}