package main

import (
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"strings"
)

func Commit(m string, rest []string) {
	// WriteTree之前没有返回值，现在改为返回treeObject
	tree := WriteTree()
	refPathBytes, err := ioutil.ReadFile(filepath.Join(".git", "HEAD"))
	if err != nil {
		log.Fatal(err)
	}
	refPathStr := string(refPathBytes)
	i := strings.LastIndex(refPathStr, "/")

	// 切片, i+1之后的str是commit对应的分支的名字
	refName := refPathStr[i+1:]
	exist, parentSha1 := isRefExist(refName)
	if !exist {
		log.Fatal("missing initial commit...")
	}
	m = getMessage(m, rest)
	commit := CommitTree(tree.Sha1, parentSha1, m, []string{})

	i = strings.Index(refPathStr, " ")
	// 空格之后的字符串是ref的绝对路径
	refPath := refPathStr[i+1:]
	err = ioutil.WriteFile(filepath.Join(".git", refPath), []byte(commit.Sha1), os.ModePerm)
	if err != nil {
		log.Fatal(err)
	}
}
