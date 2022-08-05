package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
)

// 改变我们当前所处的分支，也就是修改 .git/HEAD文件的值，我们用到 git symbolic-ref 指令
func SymbolicRef(args []string) {
	if len(args) < 3 || args[1] != "HEAD" {
		log.Fatal("error args...only support update HEAD now!")
		return
	}

	path := args[2]
	// 打印当前分支信息
	content := fmt.Sprintf("ref: %s", path)
	// 将当前分支信息写入.git/HEAD文件中
	err := ioutil.WriteFile(filepath.Join(".git", "HEAD"), []byte(content), os.ModePerm)
	if err != nil {
		log.Fatal(err)
	}
}
