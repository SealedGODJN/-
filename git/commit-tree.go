package gitHJN

import (
	"bytes"
	"fmt"
	"io/ioutil"
	"log"
	"path/filepath"
	"strings"
	"time"
)

func CommitTree(treeObjSha1 string, p string, m string) {
	if treeObjSha1 == "" || len(treeObjSha1) < 4 {
		fmt.Printf("Not a valid object name %s\n", treeObjSha1)
		return
	}
	var commitObj CommitObject

	// get the whole sha1 of the tree object
	exist, treeObjSha1 := isObjectExist(treeObjSha1)
	if !exist {
		// 如果tree object 不存在，则无法commit tree
		fmt.Printf("The tree object is not exist")
		return
	}
	commitObj.treeObjSha1 = treeObjSha1
	if p != "" {
		// TODO: 应该还要验证这个parentSha1对应的object的type
		exist, parentSha1 := isObjectExist(p)
		// parent commit不存在
		if !exist {
			fmt.Printf("The parent commit object is not exist!")
			return
		}
		parentObjectType, _, _ := getObjectType(parentSha1)
		if parentObjectType != "commit" {
			fmt.Printf("The parent commit object inputed is not commit object!")
			return
		}
		commitObj.parent = parentSha1
	}

	// read .gitConfig
	// TODO: read from .gitconfig
	commitObj.author = "SealedGodJn<1286039722@qq.com>"
	commitObj.committer = "SealedGodJn<1286039722@qq.com>"

	commitObj.date = fmt.Sprintf("%s", time.Now())
	commitObj.message = m

	objSha1, data := getSha1AndRawData(&commitObj)
	commitObj.Sha1 = objSha1
	writeObject(objSha1, data)

	fmt.Sprintf("%s\n", objSha1)
}

func getObjectType(objectId string) (string, string, string) {
	objectDir := filepath.Join(".git", "objects", objectId[:2])
	dir, err := ioutil.ReadDir(objectDir)
	// 判断读目录里面的文件时是否出错
	if err != nil {
		log.Fatal(err)
	}

	var data []byte

	// 遍历dir里面的所有文件
	// 找到符合自己要求的文件
	for _, file := range dir {
		if strings.HasPrefix(file.Name(), objectId[2:]) {
			// 该文件名的前缀与命令行参数中输入的objectId的前缀相符
			// 读取该文件名对应的文件
			data, err = ioutil.ReadFile(filepath.Join(objectDir, file.Name()))
			if err != nil {
				log.Fatal(err)
			}
		}
	}

	// 解压缩object文件
	raw := unCompressData(data)
	i := bytes.IndexByte(raw, ' ')
	j := bytes.IndexByte(raw, '\u0000')

	// raw的格式为: blob len(data)+\u0000+data
	objectType := raw[:i]
	objectSize := raw[i+1 : j]
	objectContent := raw[j+1:]

	return string(objectType), string(objectSize), string(objectContent)
}
