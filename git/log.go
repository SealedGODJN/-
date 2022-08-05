package main

import (
	"bytes"
	"fmt"
	"io/ioutil"
	"log"
	"path/filepath"
	"strings"
)

// 之前没有reference，现在存在reference，因此，对于git log master，需要去commit中寻找对应的分支
func Log(args []string) {
	// if objSha1 == "" || len(objSha1) < 4 {
	// 	fmt.Printf("Not a valid object name %s", objSha1)
	// 	return
	// }

	// exist, curObjsha1 := isObjectExist(objSha1)
	// if !exist {
	// 	fmt.Printf("Not a valid object name %s\n", objSha1)
	// 	return
	// }

	var curObjsha1 string
	if len(args) <= 1 {
		curObjsha1 = logWithoutArgs()
	} else {
		curObjsha1 = logWithArgs(args)
	}

	objStr := getCatFileStr(true, false, false, []string{curObjsha1})
	var commitObj CommitObject
	commitObj.Sha1 = curObjsha1
	commitObj.parseCommitObj([]byte(objStr))

	var buf bytes.Buffer
	printLog(&commitObj, &buf)
	fmt.Printf("%s", buf.Bytes())
}

func logWithoutArgs() string {
	// get objSha1 from HEAD
	bytes, err := ioutil.ReadFile(filepath.Join(".git", "HEAD"))
	if err != nil {
		log.Fatal(err)
	}
	s := string(bytes)
	i := strings.Index(s, " ")
	// 读取的文件内容示例： refs: /refs/heads/master
	path := s[i+1:]

	sha1bytes, err := ioutil.ReadFile(filepath.Join(".git", path))
	if err != nil {
		log.Fatal(err)
	}
	return string(sha1bytes)
}

func logWithArgs(args []string) string {
	argsStr := args[1]
	exist, curObjsha1 := isObjectExist(argsStr)
	if !exist {
		exist, curObjsha1 = isRefExist(argsStr)
		if !exist {
			log.Fatalf("Not a valid object name or reference name %s\n", argsStr)
		}
	}
	return curObjsha1
}

func printLog(commit *CommitObject, buf *bytes.Buffer) {
	buf.WriteString(fmt.Sprintf("commit %s\n", commit.Sha1))
	buf.WriteString(fmt.Sprintf("Author: %s\n", commit.author))
	buf.WriteString(fmt.Sprintf("Date: %s\n", commit.date))
	buf.WriteString(fmt.Sprintf("\n"))
	buf.WriteString(fmt.Sprintf("%s\n", commit.message))
	buf.WriteString(fmt.Sprintf("\n"))

	if commit.parent != "" {
		exist, parentSha1 := isObjectExist(commit.parent)
		if exist {
			var parent CommitObject
			parent.Sha1 = parentSha1
			objStr := getCatFileStr(true, false, false, []string{parentSha1})
			parent.parseCommitObj([]byte(objStr))
			printLog(&parent, buf)
		}
	}
}
