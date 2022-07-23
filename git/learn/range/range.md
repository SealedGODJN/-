# Range

for 循环的 range 格式可以对 slice、map、数组、字符串等进行迭代循环。格式如下：

```
for key, value := range oldMap {
    newMap[key]= value
}
```

以上代码中的 key 和 value 是可以省略。

如果只想读取 key，格式如下：

```
for key := range oldMap
```

或者这样：

for key, _ := range oldMap

如果只想读取 value，格式如下：

```
for _, value := range oldMap
```

## 代码示例1

```go
package main

import "fmt"

var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}
func main() {
	for k,v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}
}

```

输出：

```go
2**0 = 1
2**1 = 2
2**2 = 4
2**3 = 8
2**4 = 16
2**5 = 32
2**6 = 64
2**7 = 128
```

## 代码示例2

```go

package main

import "fmt"

func main() {
	map1 := make(map[int]float32)
	map[1] = 1.0
	map[2] = 2.0
	map[3] = 3.0
	map[4] = 4.0

	// 读取key和value
	for k, v := range map1 {
		fmt.Printf("key:%d value:%f\n", k, v)
	}

	// 读取key
	for key := range map1 {
		fmt.Printf("key:%d\n", key)
	}
	// 读取value
	for value := range map1 {
		fmt.Printf("value:%f\n", value)
	}
        //range也可以用来枚举 Unicode 字符串。第一个参数是字符的索引，第二个是字符（Unicode的值）本身。
        for i, c := range "go" {
        	fmt.Println(i, c)
        }
}
```

输出：

```go
key is: 4 - value is: 4.000000
key is: 1 - value is: 1.000000
key is: 2 - value is: 2.000000
key is: 3 - value is: 3.000000
key is: 1
key is: 2
key is: 3
key is: 4
value is: 1.000000
value is: 2.000000
value is: 3.000000
value is: 4.000000
```
