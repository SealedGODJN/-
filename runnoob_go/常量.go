package main

import (
	"fmt"
	"unsafe"
)

const (
	a = "abc"
	b = len(a)           // len 是内置函数
	c = unsafe.Sizeof(a) // 常量表达式中，函数必须是内置函数，否则编译不过
	// 字符串类型在 go 里是个结构, 包含指向底层数组的指针和长度,这两部分每部分都是 8 个字节，所以字符串类型大小为 16 个字节。
)

func main() {
	println(a, b, c)

	// const (
	// 	a = iota
	// 	b = iota
	// 	c = iota
	// )

	// 第一个 iota 等于 0，每当 iota 在新的一行被使用时，它的值都会自动加 1；所以 a=0, b=1, c=2 可以简写为如下形式：

	// const (
	// 	a = iota
	// 	b
	// 	c
	// )
	// fmt.Println(a, b, c)

	const (
		a = iota //0
		b        //1
		c        //2
		d = "ha" //独立值，iota += 1
		e        //"ha"   iota += 1
		f = 100  //iota +=1
		g        //100  iota +=1
		h = iota //7,恢复计数
		i        //8
	)
	fmt.Println(a, b, c, d, e, f, g, h, i)

	// iota 只是在同一个 const 常量组内递增，每当有新的 const 关键字时，iota 计数会重新开始。

	const (
		m = 1 << iota
		n = 3 << iota
		k // k = 3<<2
		l // l = 3<<3
		// <<n==*(2^n)
		// 原因：在定义常量组时，如果不提供初始值，则表示将使用上行的表达式。
	)
	fmt.Println("m=", m)
	fmt.Println("n=", n)
	fmt.Println("k=", k)
	fmt.Println("l=", l)
}
