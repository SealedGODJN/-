# byte包

## Buffer

是一个拥有read和write方法且可变尺寸的byte缓冲区？缓冲区的0值是一个可使用的空缓冲区

```go
type Buffer struct {
	// contains filtered or unexported fields
}
```


### 示例

```go
package main

import (
	"bytes"
	"fmt"
	"os"
)

func main() {
	var b bytes.Buffer // A Buffer needs no initialization.
	b.Write([]byte("Hello "))
	fmt.Fprintf(&b, "world!")
	b.WriteTo(os.Stdout)
}
```

Buffer不需要初始化就可以使用

#### 输出结果

```go
Hello world!
```

### 函数接口

#### 1 func (*Buffer) [WriteTo](https://cs.opensource.google/go/go/+/go1.18.3:src/bytes/buffer.go;l=236)[¶](https://pkg.go.dev/bytes#Buffer.WriteTo)

```go
func (b *Buffer) WriteTo(w io.Writer) (n int64, err error)
```

WriteTo writes data to w until the buffer is drained or an error occurs. The return value n is the number of bytes written; it always fits into an int, but it is int64 to match the io.WriterTo interface. Any error encountered during the write is also returned.
