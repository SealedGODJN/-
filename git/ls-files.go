package main

import (
	"fmt"
	"os"
)

/*
git ls-files 用来查看暂存区和工作区的文件信息，同样有很多参数，我们只实现 --stage，查看暂存区的内容（不带参数的 ls-files 指令是列出当前目录包括子目录下的所有文件）。
实现流程：从 index 文件中读取暂存区的内容，解压后按照一定的格式打印到标准输出。
*/
func LsFiles(s bool) {
	if _, err := os.Stat(indexPath); os.IsNotExist(err) {
		return
	}

	// read entry-list from index
	entryList := getEntryListFromIndex()

	for _, entry := range entryList.List {
		fmt.Printf("%s %s %d %s\n", entry.Mode, entry.Sha1, entry.Num, entry.Path)
	}

}
