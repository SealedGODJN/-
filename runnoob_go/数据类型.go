package main

import (
	"fmt"
	"strings"
)

var isActive bool                   // 全局声明
var enabled, disabled = true, false // 忽略类型的声明

// valid := false // 简短声明
// isActive = true // 赋值操作

func main() {
	// fmt.Println(enabled, disabled, valid, isActive)
	fmt.Println(enabled, disabled, isActive)

	str := "这里是 www\n.runoob\n.com"
	fmt.Println("-------- 原字符串 ----------")
	fmt.Println(str)
	// 去除空格
	str = strings.Replace(str, " ", "", -1)
	// 去除换行符
	str = strings.Replace(str, "\n", "", -1)
	fmt.Println("-------- 去除空格与换行后 ----------")
	fmt.Println(str)

	var a1 int = 1
	var a2 float32 = 1.11111111111111110                                                // 小数点后16个1
	var a3 float64 = 1.1111111111111111111111111111111111111111111111111111111111111111 // 小数点后64个1
	var a4 uint8 = 255                                                                  // 8个1
	var a5 uint16 = 65535                                                               // 16个1
	var a6 uint32 = 4294967295                                                          // 32个1
	var a7 uint64 = 18446744073709551615                                                // 64个1
	var a8 int8 = -128                                                                  // 8个1
	var a9 int16 = -32768                                                               // 16个1
	var a10 int32 = -2147483648                                                         // 32个1
	var a11 int64 = -9223372036854775808                                                // 64个1
	fmt.Println(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)
}
