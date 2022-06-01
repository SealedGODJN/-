package main

import (
	"fmt"
)

//这种不带声明格式的只能在函数体中出现
//g, h := 123, "hello"

func main() {
	var a1 *int
	var a2 []int
	var a3 map[string]int
	var a4 chan int
	var a5 func(string) int
	var a6 error // error 是接口

	fmt.Println(a1, a2, a3, a4, a5, a6)

	// 	var intVal int
	//  intVal :=1 // 这时候会产生编译错误，因为 intVal 已经声明，不需要重新声明

	intVal := 1 // 此时不会产生编译错误，因为有声明新的变量，因为 := 是一个声明语句
	f := "Runnoob"
	fmt.Println(intVal, f)

	// // 这种因式分解关键字的写法一般用于声明全局变量
	// var (
	// 	vname1 v_type1
	// 	vname2 v_type2
	// )

	// 如果你声明了一个局部变量却没有在相同的代码块中使用它，同样会得到编译错误
	// 全局变量是允许声明但不使用的

	// 如果你想要交换两个变量的值，则可以简单地使用 a, b = b, a，两个变量的类型必须是相同。

	// 空白标识符 _ 也被用于抛弃值，如值 5 在：_, b = 5, 7 中被抛弃。
	// _ 实际上是一个只写变量，你不能得到它的值。这样做是因为 Go 语言中你必须使用所有被声明的变量，但有时你并不需要使用从一个函数得到的所有返回值。

}
