package main

func main() {
	// /* 定义局部变量 */
	// var a int = 20

	// /* 使用 if 语句判断布尔表达式 */
	// if a < 20 {
	// 	/* 如果条件为 true 则执行以下语句 */
	// 	fmt.Printf("a 小于 20\n")
	// } else {
	// 	fmt.Printf("a 大于等于 20\n")
	// }

	// /* 定义局部变量 */
	// var grade string = "B"
	// var marks int = 90

	// switch marks {
	// case 90:
	// 	grade = "A"
	// case 80:
	// 	grade = "B"
	// case 50, 60, 70:
	// 	grade = "C"
	// default:
	// 	grade = "D"
	// }

	// switch {
	// case grade == "A":
	// 	fmt.Printf("优秀!\n")
	// case grade == "B", grade == "C":
	// 	fmt.Printf("良好\n")
	// case grade == "D":
	// 	fmt.Printf("及格\n")
	// case grade == "F":
	// 	fmt.Printf("不及格\n")
	// default:
	// 	fmt.Printf("差\n")
	// }
	// fmt.Printf("你的等级是 %s\n", grade)

	// /*
	// 	1、每个 case 都必须是一个通信
	// 	2、所有 channel 表达式都会被求值
	// 	3、所有被发送的表达式都会被求值
	// 	4、如果任意某个通信可以进行，它就执行，其他被忽略。
	// 	5、如果有多个 case 都可以运行，Select 会随机公平地选出一个执行。其他不会执行。
	// 		否则：
	// 			如果有 default 子句，则执行该语句。
	// 			如果没有 default 子句，select 将阻塞，直到某个通信可以运行；Go 不会重新对 channel 或值进行求值。
	// */
	// var c1, c2, c3 chan int
	// var i1, i2 int
	// select {
	// case i1 = <-c1:
	// 	fmt.Printf("received ", i1, " from c1\n")
	// case c2 <- i2:
	// 	fmt.Printf("sent ", i2, " to c2\n")
	// case i3, ok := (<-c3): // same as: i3, ok := <-c3
	// 	if ok {
	// 		fmt.Printf("received ", i3, " from c3\n")
	// 	} else {
	// 		fmt.Printf("c3 is closed\n")
	// 	}
	// default:
	// 	fmt.Printf("no communication\n")
	// }

}
