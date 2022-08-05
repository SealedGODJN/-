package main

import (
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"strings"
)

// func UpdateRef(reference string, objSha string) {
func UpdateRef(args []string) {
	if len(args) < 3 {
		log.Fatal("missing args\n")
		return
	}

	// args[0] 是 update-ref
	path := args[1]
	objSha1 := args[2]
	path = filepath.Join(".git", path)

	_, err := os.Create(path)
	if err != nil {
		log.Fatal(err)
	}

	_, objSha1 = isObjectExist(objSha1)
	objType := getCatFileStr(false, true, false, []string{objSha1})
	// 我认为：还需要判断这个obj的type是commit object
	if !strings.Contains(objType, "commit") {
		log.Fatal("This object is not a commit object")
		return
	}

	// 将reference信息写入/.git/refs/head/path文件
	err = ioutil.WriteFile(path, []byte(objSha1), os.ModePerm)
	if err != nil {
		log.Fatal(err)
	}
}

func isRefExist(ref string) (bool, string) {
	path := filepath.Join(".git", "refs", "heads", ref)
	if _, err := os.Stat(path); os.IsNotExist(err) {
		return false, ""
	}
	bytes, err := ioutil.ReadFile(path)
	if err != nil {
		log.Fatal(err)
	}
	return true, string(bytes)
}
