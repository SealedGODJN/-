package main

import (
	"fmt"
)

func main() {
	var a int = 4
	var ptr *int

	ptr = &a

	fmt.Printf("a的值为 %d\n", a)

	a = 5
	fmt.Printf("*ptr 为 %d\n", *ptr)
}
