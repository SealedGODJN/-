package main

import (
	"fmt"
	"log"
	"time"
)

func CommitTree(treeObjSha1 string, p string, m string, rest []string) *CommitObject {
	if treeObjSha1 == "" || len(treeObjSha1) < 4 {
		log.Fatalf("Not a valid object name %s\n", treeObjSha1)
		// return nil
	}
	var commitObj CommitObject

	// get the whole sha1 of the tree object
	exist, treeObjSha1 := isObjectExist(treeObjSha1)
	if !exist {
		// 如果tree object 不存在，则无法commit tree
		log.Fatalf("The tree object is not exist")
		// return
	}
	commitObj.treeObjSha1 = treeObjSha1

	if p != "" {
		// TODO: 应该还要验证这个parentSha1对应的object的type（parent是commit object才能设置为本次commit的parent）
		exist, parentSha1 := isObjectExist(p)
		// parent commit不存在
		if !exist {
			log.Fatalf("The parent commit object is not exist!")
			// return
		}
		// parentObjectType := getCatFileStr(false, true, false, []string{parentSha1})
		// if strings.Compare(parentObjectType, "commit") != 0 {
		// 	log.Fatalf("The parent commit object inputed is not commit object!")
		// 	// return
		// }
		commitObj.parent = parentSha1
	}

	// read .gitConfig
	// TODO: read from .gitconfig
	commitObj.author = "SealedGodJn<1286039722@qq.com>"
	commitObj.committer = "SealedGodJn<1286039722@qq.com>"

	// commitObj.date = fmt.Sprintf("%s", time.Now())
	commitObj.date = time.Now().String()
	// commitObj.message = m
	commitObj.message = getMessage(m, rest)

	objSha1, data := getSha1AndRawData(&commitObj)
	commitObj.Sha1 = objSha1
	writeObject(objSha1, data)

	fmt.Printf("%s\n", objSha1)
	return &commitObj
}

func getMessage(m string, rest []string) string {
	for _, s := range rest {
		m += " " + s
	}
	return m
}
