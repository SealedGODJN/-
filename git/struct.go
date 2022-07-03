package gitHJN

import (
	"bytes"
	"fmt"
	"io/ioutil"
	"log"
)

type EntryList struct {
	List []Entry
}

type Entry struct {
	Mode string // 10644
	Sha1 string
	Num  int
	Path string
	Type string
}

// type TreeObject struct {
// 	List []Entry
// 	sha1 string
// }

// 重构代码 2022.7.3

type Object interface {
	getContent() []byte
	getType() string
}

type BlobObject struct {
	Path string
	Sha1 string
	t    string // 参数 -t
}

func (blob *BlobObject) getContent() []byte {
	file, err := ioutil.ReadFile(blob.Path)
	if err != nil {
		log.Fatal(err)
	}
	return file
}

func (blob *BlobObject) getType() string {
	return "blob"
}

type TreeObject struct {
	List []Entry
	Sha1 string
	t    string // 新增
}

func (tree *TreeObject) getContent() []byte {
	var bytes bytes.Buffer
	for _, entry := range tree.List {
		bytes.WriteString(fmt.Sprintf("%s %s %s %s\n", entry.Mode, entry.Type, entry.Sha1, entry.Path))
	}
	return bytes.Bytes()
}

func (tree *TreeObject) getType() string {
	return "tree"
}
